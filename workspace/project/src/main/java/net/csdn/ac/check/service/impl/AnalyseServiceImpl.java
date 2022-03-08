package net.csdn.ac.check.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.dao.MySQLDao;
import net.csdn.ac.check.core.utils.ResultUtil;
import net.csdn.ac.check.entity.CheckRecord;
import net.csdn.ac.check.entity.MapRowMapper;
import net.csdn.ac.check.param.CheckRecordParam;
import net.csdn.ac.check.service.AnalyseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据给定筛选条件进行搜索
     *
     * @param checkRecordParam 搜索条件
     * @return
     */
    @Override
    public JSONObject monthSearch(CheckRecordParam checkRecordParam) {
        StringBuilder sb = new StringBuilder();
        LocalDate startDate = LocalDate.parse(checkRecordParam.getStartDate(), DateTimeFormatter.ISO_DATE);
        LocalDate endDate = LocalDate.parse(checkRecordParam.getEndDate(), DateTimeFormatter.ISO_DATE);
        LocalDateTime startTime = startDate.atStartOfDay();
        LocalDateTime endTime = endDate.atTime(23, 59, 59);
        long startDateEpochSecond = startTime.atZone(ZoneOffset.systemDefault()).toInstant().getEpochSecond();
        long endDateEpochSecond = endTime.atZone(ZoneOffset.systemDefault()).toInstant().getEpochSecond();
        String[] departId = checkRecordParam.getDepartId();
        if (departId.length == 0 || departId.length == 1 && "-1".equals(departId[0])) {
            logger.info("未传入部门id，查询全部部门的打卡数据");
        }
//        sb.append("SELECT id, user_id, check_time, address_id FROM check_record ")
//                .append("WHERE check_time >= ? AND check_time <= ?  LIMIT ? OFFSET ?");
        sb.append("SELECT DISTINCTROW cr.user_id as user_id, u.real_name as real_name, r.title rule_name, r.id as rule_id, cr.address_id as address_id ")
                .append("FROM `check_record` cr ")
                .append("INNER JOIN `user` u ON cr.user_id = u.id ")
                .append("INNER JOIN `rel_user_rule` rur ON cr.user_id = rur.user_id ")
                .append("INNER JOIN `rule` r ON r.id = rur.rule_id ")
                .append("ORDER BY user_id");
        /**
         * SELECT DISTINCTROW cr.user_id user_id, u.real_name real_name, r.title rule_name,  r.id as rule_id, cr.address_id address_id
         * FROM `check_record` cr
         * INNER JOIN `user` u ON cr.user_id = u.id
         * INNER JOIN `rel_user_rule` rur ON cr.user_id = rur.user_id
         * INNER JOIN `rule` r ON r.id = rur.rule_id
         * ORDER BY user_id
         */
//        List<Map<String, Object>> checkRecordList = (List<Map<String, Object>>) mySQLDao.find(sb.toString(), new MapRowMapper(), startDateEpochSecond, endDateEpochSecond, checkRecordParam.getSize(), checkRecordParam.getStart());
        List<Map<String, Object>> checkRecordList = (List<Map<String, Object>>) mySQLDao.find(sb.toString(), new MapRowMapper());
        for (Map<String, Object> map : checkRecordList) {
            map.put("abnormal_total", new HashMap<>());
            map.put("late_total", new HashMap<>());
            map.put("early_total", new HashMap<>());
        }
        JSONObject result = new JSONObject();
        result.put("count", checkRecordList.size());
        result.put("list", checkRecordList);
        return result;
    }


    private JSONObject defaultSearchResult() {
        return ResultUtil.content(JSON.parseObject("{\n" +
                "        \"count\": 1,\n" +
                "        \"list\": [\n" +
                "            {\n" +
                "                \"user_id\": 1,\n" +
                "                \"real_name\": \"白月光\",\n" +
                "                \"rule_name\": \"长沙CSDN\",\n" +
                "                \"should_days\": 9,\n" +
                "                \"normal_days\": 6,\n" +
                "                \"abnormal_days\": 3,\n" +
                "                \"standard_duration\": 81,\n" +
                "                \"actual_duration\": 90,\n" +
                "                \"abnormal_total\": {\n" +
                "                    \"total\": 4,\n" +
                "                    \"time\": 200\n" +
                "                },\n" +
                "                \"late_total\": {\n" +
                "                    \"total\": 1,\n" +
                "                    \"time\": 15\n" +
                "                },\n" +
                "                \"early_total\": {\n" +
                "                    \"total\": 1,\n" +
                "                    \"time\": 15\n" +
                "                },\n" +
                "                \"missing\": 1,\n" +
                "                \"abnormal_location\": 1\n" +
                "            }\n" +
                "        ]\n" +
                "    }"));
    }


}
