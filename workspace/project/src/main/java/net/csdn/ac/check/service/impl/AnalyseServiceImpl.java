package net.csdn.ac.check.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.dao.MySQLDao;
import net.csdn.ac.check.entity.MapRowMapper;
import net.csdn.ac.check.param.CheckRecordParam;
import net.csdn.ac.check.service.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 月度汇总 Service 实现类
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/5 0:22
 */
@Service("analyseService")
public class AnalyseServiceImpl extends BaseObject implements AnalyseService {
    @Resource
    private MySQLDao mySQLDao;

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    /**
     * 工作开始时间
     */
    //@Value("${work.startTime:上午09:00}")
    private LocalTime workStartTime;
    /**
     * 工作结束时间
     */
    //@Value("${work.endTime:下午06:00}")
    private LocalTime workEndTime;

    private long workHours;

    @PostConstruct
    private void init() {
        this.workStartTime = LocalTime.of(9,0);
        this.workEndTime = LocalTime.of(18,0);
        this.workHours = ChronoUnit.HOURS.between(this.workStartTime, this.workEndTime);
    }

    /**
     * 根据给定筛选条件进行搜索
     *
     * @param checkRecordParam 搜索条件
     * @return
     */
    @Override
    @Cacheable(value = "monthRecordSearch", key = "#checkRecordParam.cacheKey", unless="#result == null")
    public JSONObject monthSearch(CheckRecordParam checkRecordParam) {
        LocalDate startDate = LocalDate.parse(checkRecordParam.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(checkRecordParam.getEndDate(), DateTimeFormatter.ISO_DATE);
        // 应打卡天数
        long shouldDays = Period.between(startDate, endDate).getDays() + 1;
        // 标准工作时长
        long standardDuration = shouldDays * workHours;
        // 需要查询的用户id
        List<Integer> userIdList = getUserIdList(checkRecordParam.getDepartId());
        // 用户签到规则以及签到地点
        Map<Long, Map<String, Object>> userRuleAndAddress = getUserRuleAndAddress(userIdList);
        Map<Long, Map<String, Object>> userRecordStatistics = getUserRecordStatistics(userIdList, startDate, endDate, userRuleAndAddress);
        List<Map<String, Object>> userRuleList = new ArrayList<>(userRuleAndAddress.values());
        for (Map<String, Object> userRule : userRuleList) {
            userRule.remove("address_ids");
            userRule.remove("rule_id");
            // 应打卡天数
            userRule.put("should_days", shouldDays);
            // 标准工作时长
            userRule.put("standard_duration", standardDuration);
            userRule.putAll(userRecordStatistics.get(userRule.get("user_id")));
        }
        Integer start = checkRecordParam.getStart();
        Integer size = checkRecordParam.getSize();
        List<Map<String, Object>> currentPageDataList = userRuleList.subList(Math.max(0, (start - 1) * size), Math.min(userRuleList.size(), start * size));
        JSONObject result = new JSONObject();
        result.put("count", userRuleList.size());
        result.put("list", new ArrayList<>(currentPageDataList));
        return result;
    }

    /**
     * 获得用户签到信息
     *
     * @param userIdList         用户id集合
     * @param startDate          开始日期
     * @param endDate            结束日期
     * @param userRuleAndAddress 用户签到规则信息
     */
    private Map<Long, Map<String, Object>> getUserRecordStatistics(List<Integer> userIdList, LocalDate startDate, LocalDate endDate, Map<Long, Map<String, Object>> userRuleAndAddress) {
        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = endDate.atTime(23, 59, 59);
        Map<String, Object> params = new HashMap<>();
        long startDateEpochSecond = startTime.atZone(ZoneOffset.systemDefault()).toInstant().getEpochSecond();
        long endDateEpochSecond = endTime.atZone(ZoneOffset.systemDefault()).toInstant().getEpochSecond();
        // 应打卡天数
        long shouldDays = Period.between(startDate, endDate).getDays() + 1;
        params.put("startDate", startDateEpochSecond);
        params.put("endDate", endDateEpochSecond);
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT user_id, check_time, address_id FROM `check_record` ")
                .append(" WHERE check_time >= :startDate ")
                .append(" AND check_time <= :endDate ");
        if (userIdList != null && !userIdList.isEmpty()) {
            sb.append(" AND user_id IN (:userIds) ");
            params.put("userIds", userIdList);
        }
        sb.append(" ORDER BY check_time ");
        sb.append(";");
        //noinspection unchecked
        List<Map<String, Object>> userRecordList = mySQLDao.find(sb.toString(), params, new MapRowMapper());
        // 按用户id和签到日期进行分组
        Map<Integer, Map<LocalDate, List<Map<String, Object>>>> userRecordGroupByUserAndCheckTime = userRecordList.stream().collect(Collectors.groupingBy(userRecord -> (Integer) userRecord.get("user_id"), Collectors.groupingBy(userRecord -> {
            Integer checkTime = (Integer) userRecord.get("check_time");
            LocalDate localDate = Instant.ofEpochSecond(checkTime).atZone(ZoneOffset.systemDefault()).toLocalDate();
            return localDate;
        })));
        // 用户签到统计信息
        Map<Long, Map<String, Object>> userRecordStatistics = new HashMap<>();
        userRecordGroupByUserAndCheckTime.forEach((userId, userRecordInfoList) -> {
            Map<String, Object> userRule = userRuleAndAddress.get((long) userId);
            // 实际工作时长
            long actualDuration = 0;
            // 正常打卡天数
            long normalDays = 0;
            // 异常 abnormal_total total time
            // 异常天数
            long abnormalTotal = shouldDays - userRecordInfoList.size();
            // 异常时间 分钟
            long abnormalTime = 0;
            // 迟到 late_total total time
            // 迟到天数
            long lateTotal = 0;
            // 迟到时间 分钟
            long lateTime = 0;
            // 早退 early_total total time
            // 早退天数
            long earlyTotal = 0;
            // 迟到时间 分钟
            long earlyTime = 0;
            // 缺卡
            long missing = shouldDays - userRecordInfoList.size();
            // 地点异常
            long abnormalLocation = 0;
            Set<Map.Entry<LocalDate, List<Map<String, Object>>>> dateRecordEntrySet = userRecordInfoList.entrySet();
            for (Map.Entry<LocalDate, List<Map<String, Object>>> dateRecordEntry : dateRecordEntrySet) {
                // 每天的签到信息
                List<Map<String, Object>> dateRecordEntryValue = dateRecordEntry.getValue();
                if (dateRecordEntryValue.isEmpty()) {
                    // 缺卡
                    missing++;
                    continue;
                } else if (dateRecordEntryValue.size() == 1) {
                    // 首次签到信息
                    Map<String, Object> firstRecord = dateRecordEntryValue.get(0);
                    Integer firstCheckTime = (Integer) firstRecord.get("check_time");
                    LocalTime firstCheckLocalTime = Instant.ofEpochSecond(firstCheckTime).atZone(ZoneOffset.systemDefault()).toLocalTime();
                    if (!firstCheckLocalTime.isAfter(workStartTime) && !firstCheckLocalTime.isBefore(workEndTime)) {
                        // 只打一次卡且在正常上班时间内 为上班迟到
                        lateTotal++;
                        abnormalTotal++;
                    } else {
                        // 否则为缺卡
                        missing++;
                        abnormalTotal++;
                    }
                } else {
                    // 首次签到信息
                    Map<String, Object> firstRecord = dateRecordEntryValue.get(0);
                    Integer firstCheckTime = (Integer) firstRecord.get("check_time");
                    LocalTime firstCheckLocalTime = Instant.ofEpochSecond(firstCheckTime).atZone(ZoneOffset.systemDefault()).toLocalTime();
                    // 末次签到信息
                    Map<String, Object> lastRecord = dateRecordEntryValue.get(dateRecordEntryValue.size() - 1);
                    Integer endCheckTime = (Integer) lastRecord.get("check_time");
                    LocalTime endCheckLocalTime = Instant.ofEpochSecond(endCheckTime).atZone(ZoneOffset.systemDefault()).toLocalTime();
                    // 工作时长分钟
                    long workTimeMin = Duration.between(firstCheckLocalTime, endCheckLocalTime).toMinutes();
                    actualDuration += workTimeMin;
                    if (firstCheckLocalTime.isAfter(workStartTime)) {
                        // 迟到
                        lateTotal++;
                        abnormalTotal++;
                        lateTime += Duration.between(workStartTime, firstCheckLocalTime).toMinutes();
                        abnormalTime += Duration.between(workStartTime, firstCheckLocalTime).toMinutes();
                    }
                    if (endCheckLocalTime.isBefore(workEndTime)) {
                        // 早退
                        earlyTotal++;
                        abnormalTotal++;
                        earlyTime += Duration.between(endCheckLocalTime, workEndTime).toMinutes();
                        abnormalTime += Duration.between(endCheckLocalTime, workEndTime).toMinutes();
                    }
                    if (!firstCheckLocalTime.isAfter(workStartTime) && !endCheckLocalTime.isBefore(workEndTime)) {
                        // 正常
                        normalDays++;
                    }
                }
                // 查找不正确的地点
                List<Integer> addressIds = (List<Integer>) userRule.get("address_ids");
                for (Map<String, Object> dateRecord : dateRecordEntryValue) {
                    Integer addressId = (Integer) dateRecord.get("address_id");
                    if (!addressIds.contains(addressId)) {
                        abnormalLocation++;
                    }
                }
            }
            Map<String, Object> userRecordMap = new HashMap<>();
            userRecordMap.put("normal_days", normalDays);
            userRecordMap.put("abnormal_days", shouldDays - normalDays);
            userRecordMap.put("actual_duration", TimeUnit.MINUTES.toHours(actualDuration));
            userRecordMap.put("missing", missing);
            userRecordMap.put("abnormal_location", abnormalLocation);
            Map<String, Object> abnormalTotalMap = new HashMap<>();
            abnormalTotalMap.put("total", abnormalTotal);
            abnormalTotalMap.put("time", abnormalTime);
            userRecordMap.put("abnormal_total", abnormalTotalMap);
            Map<String, Object> lateTotalMap = new HashMap<>();
            lateTotalMap.put("total", lateTotal);
            lateTotalMap.put("time", lateTime);
            userRecordMap.put("late_total", lateTotalMap);
            Map<String, Object> earlyTotalMap = new HashMap<>();
            earlyTotalMap.put("total", earlyTotal);
            earlyTotalMap.put("time", earlyTime);
            userRecordMap.put("early_total", earlyTotalMap);
            userRecordStatistics.put((long) userId, userRecordMap);
        });
        return userRecordStatistics;
    }

    /**
     * 通过部门id获取用户id，若未传部门id或部门id为-1则返回 null
     *
     * @param deptIds 部门id数组
     */
    private List<Integer> getUserIdList(String[] deptIds) {
        if (deptIds.length == 0 || deptIds.length == 1 && "-1".equals(deptIds[0])) {
            return null;
        } else {
            String sql = " SELECT user_id FROM `rel_department_user` WHERE dept_id IN (:deptIds);";
            //noinspection unchecked
            List<Map<String, Object>> userList = mySQLDao.find(sql, Collections.singletonMap("deptIds", Arrays.asList(deptIds)), new MapRowMapper());
            return userList.stream().map(m -> m.get("user_id")).map(Integer.class::cast).collect(Collectors.toList());
        }
    }

    /**
     * 获得用户打卡规则和地址信息
     *
     * @param userIdList 用户id集合
     * @return
     */
    private Map<Long, Map<String, Object>> getUserRuleAndAddress(List<Integer> userIdList) {

        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT DISTINCTROW u.id as user_id, u.real_name as real_name, r.id rule_id, r.title rule_name ")
                .append(" FROM `user` u ")
                .append(" INNER JOIN `rel_user_rule` rur ON u.id = rur.user_id ")
                .append(" INNER JOIN `rule` r ON r.id = rur.rule_id ");
        if (userIdList != null && !userIdList.isEmpty()) {
            sb.append(" WHERE u.id IN (:userIds) ");
        }
        sb.append(";");
        //noinspection unchecked
        List<Map<String, Object>> userRuleList = (List<Map<String, Object>>) mySQLDao.find(sb.toString(), Collections.singletonMap("userIds", userIdList), new MapRowMapper());
        List<String> ruleIdList = userRuleList.stream().map(m -> (String) m.get("rule_id")).collect(Collectors.toList());
        String addressSql = "SELECT rra.rule_id as rule_id, rra.address_id as address_id FROM `rel_rule_address` rra WHERE rra.rule_id IN (:ruleIds)";
        //noinspection unchecked
        List<Map<String, Object>> ruleAddressList = (List<Map<String, Object>>) mySQLDao.find(addressSql, Collections.singletonMap("ruleIds", ruleIdList), new MapRowMapper());
        // 规则->签到地点映射
        Map<String, List<Integer>> ruleAddressMap = ruleAddressList.stream().collect(Collectors.groupingBy(m -> (String) m.get("rule_id"), Collectors.mapping(m -> (Integer) m.get("address_id"), Collectors.toList())));
        Map<Long, Map<String, Object>> userRuleMap = new HashMap<>();
        for (Map<String, Object> userRule : userRuleList) {
            String ruleId = (String) userRule.get("rule_id");
            Long userId = (Long) userRule.get("user_id");
            userRule.put("address_ids", ruleAddressMap.get(ruleId));
            userRuleMap.put(userId, userRule);
        }
        return userRuleMap;
    }

}
