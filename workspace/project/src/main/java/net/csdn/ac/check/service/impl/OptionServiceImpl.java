package net.csdn.ac.check.service.impl;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.constant.Constant;
import net.csdn.ac.check.core.utils.SystemUtil;
import net.csdn.ac.check.entity.WorkDay;
import net.csdn.ac.check.core.dao.MySQLDao;
import net.csdn.ac.check.entity.Option;
import net.csdn.ac.check.service.OptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则选项Service实现类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@Service("optionService")
public class OptionServiceImpl extends BaseObject implements OptionService {
    @Resource
    private MySQLDao mySQLDao;

    /**
     * 新建规则选项
     *
     * @param title         规则选项标题
     * @param description   规则选项描述
     * @param timeType      1：星期，2：日期
     * @param week          当timeType=1时此字段有值，具体值为[0,1,2,3]
     * @param dateday       当timeType=2时此字段有值，具体值为”2021-06-18”
     * @param type          1：固定时间上下班；2：自由上下班；3：不上班
     * @param section       type=1时，表示时段，具体值为09:00,12:00;13:00,18:00
     * @param timeLen       type=1时，该字段无值，为null；type=2时，表示时长，具体值为1～12；type=3时，表示不上班，值为null
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String add(final String title, final String description, final Integer timeType, final Integer[] week, final String[] dateday, final Integer type, final String[] section, final Integer timeLen) {
        try {
            String optionid = SystemUtil.uuid();
            long createtime = System.currentTimeMillis() / 1000;
            int code1 = -1;
            int code2 = -1;
            int code3 = -1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            Map<String, Object> map = null;
            List<Object[]> list = null;
            String[] ids = null;

            // 创建规则选项
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO options (id, title, description, type, create_time) VALUES (?, ?, ?, ?, ?)");
            code1 = mySQLDao.create(sb.toString(), optionid, title, description, timeType, createtime);
            flag1 = code1 == Constant.SUCCESS_CODE;

            // 时间类型，1：星期，2：日期
            switch (timeType) {
                case 1:// 1：星期
                    sb = new StringBuilder();
                    sb.append("INSERT INTO weekday (id, weekday, duration, type, start_time, end_time, create_time) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    // 打卡类型
                    switch (type) {
                        case 1:// 1：固定时间上下班
                            String[][] timeArr = parseArray(section);
                            map = getArgumentList(week, 0, type, timeArr, createtime);
                            break;
                        case 2:// 2：自由上下班
                            map = getArgumentList(week, timeLen, type, null, createtime);
                            break;
                        case 3:// 3：不上班
                            map = getArgumentList(week, 0, type, null, createtime);
                            break;
                    }
                    list = (List<Object[]>) map.get("list");
                    code2 = mySQLDao.batch(sb.toString(), list);
                    flag2 = code2 == Constant.SUCCESS_CODE;

                    // 创建工作日表记录
                    sb = new StringBuilder();
                    sb.append("INSERT INTO rel_workday (option_id, work_id) VALUES (?, ?)");
                    ids = (String[]) map.get("ids");
                    list = getWorkArgumentList(optionid, ids);
                    code3 = mySQLDao.batch(sb.toString(), list);
                    flag3 = code3 == Constant.SUCCESS_CODE;
                    break;
                case 2:// 2：日期
                    sb = new StringBuilder();
                    sb.append("INSERT INTO dateday (id, dateday, duration, type, start_time, end_time, create_time) VALUES (?, ?, ?, ?, ?, ?, ?)");
                    // 打卡类型
                    switch (type) {
                        case 1:// 1：固定时间上下班
                            String[][] timeArr = parseArray(section);
                            map = getArgumentList(dateday, 0, type, timeArr, createtime);
                            break;
                        case 2:// 2：自由上下班
                            map = getArgumentList(dateday, timeLen, type, null, createtime);
                            break;
                        case 3:// 3：不上班
                            map = getArgumentList(dateday, 0, type, null, createtime);
                            break;
                    }
                    list = (List<Object[]>) map.get("list");
                    code2 = mySQLDao.batch(sb.toString(), list);
                    flag2 = code2 == Constant.SUCCESS_CODE;

                    // 创建工作日表记录
                    sb = new StringBuilder();
                    sb.append("INSERT INTO rel_workday (option_id, work_id) VALUES (?, ?)");
                    ids = (String[]) map.get("ids");
                    list = getWorkArgumentList(optionid, ids);
                    code3 = mySQLDao.batch(sb.toString(), list);
                    flag3 = code3 == Constant.SUCCESS_CODE;
                    break;
            }
            // 全部保存成功才认为成功
            if (flag1 && flag2 && flag3) {
                return optionid;
            }
        } catch (Exception e) {
            logger.error("新建规则选项异常: {}", e.getMessage());
        }
        throw new RuntimeException("新建规则选项异常");
    }

    /**
     * 规则选项数量
     *
     * @return
     */
    @Override
    public Integer count() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) FROM options");
        return mySQLDao.count(sb.toString());
    }

    /**
     * 规则选项列表
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @Override
    public List<Option> queryList(final int start, final int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, description, type, create_time FROM options ORDER BY create_time DESC LIMIT ");
        sb.append((start - 1) * size).append(", ").append(size);
        return (List<Option>) mySQLDao.find(sb.toString(), new Option());
    }

    /**
     * 规则选项数量
     *
     * @param title 查询内容
     * @param type  规则类型，1：星期，2：日期
     * @return
     */
    @Override
    public Integer count(final String title, final Integer type) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) FROM options ");
        if (StringUtils.isNotBlank(title) && null != type) {
            sb.append("WHERE title LIKE '%").append(title).append("%' AND type = ").append(type);
        }
        if (StringUtils.isNotBlank(title) && null == type) {
            sb.append("WHERE title LIKE '%").append(title).append("%'");
        }
        if (StringUtils.isBlank(title) && null != type) {
            sb.append("WHERE type = ").append(type);
        }
        return mySQLDao.count(sb.toString());
    }

    /**
     * 规则选项列表
     *
     * @param title 查询内容
     * @param type  规则类型，1：星期，2：日期
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @Override
    public List<Option> queryList(final String title, final Integer type, final int start, final int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, description, type, create_time FROM options ");
        if (StringUtils.isNotBlank(title) && null != type) {
            sb.append("WHERE title LIKE '%").append(title).append("%' AND type = ").append(type).append(" ");
        }
        if (StringUtils.isNotBlank(title) && null == type) {
            sb.append("WHERE title LIKE '%").append(title).append("%' ");
        }
        if (StringUtils.isBlank(title) && null != type) {
            sb.append("WHERE type = ").append(type).append(" ");
        }
        sb.append("ORDER BY create_time DESC LIMIT ").append((start - 1) * size).append(", ").append(size);
        return (List<Option>) mySQLDao.find(sb.toString(), new Option());
    }

    /**
     * 查询规则选项
     *
     * @param id 规则选项id
     * @return
     */
    @Override
    public Option queryOne(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, description, type, create_time FROM options WHERE id = ?");
        return (Option) mySQLDao.findOne(sb.toString(), new Option(), id);
    }

    /**
     * 规则选项详情
     *
     * @param id 规则选项id
     * @return
     */
    @Override
    public List<WorkDay> WeekdayDetails(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT d.weekday, '2000-01-01' AS dateday, d.duration, d.type, d.start_time, d.end_time FROM options AS o, rel_workday AS w, weekday AS d ");
        sb.append("WHERE o.id = w.option_id AND w.work_id = d.id AND o.id = ? ORDER BY d.weekday, d.start_time");
        return (List<WorkDay>) mySQLDao.find(sb.toString(), new WorkDay(), id);
    }

    /**
     * 规则选项详情
     *
     * @param id 规则选项id
     * @return
     */
    @Override
    public List<WorkDay> DatedayDetails(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT '-1' AS weekday, d.dateday, d.duration, d.type, d.start_time, d.end_time FROM options AS o, rel_workday AS w, dateday AS d ");
        sb.append("WHERE o.id = w.option_id AND w.work_id = d.id AND o.id = ? ORDER BY d.dateday, d.start_time");
        return (List<WorkDay>) mySQLDao.find(sb.toString(), new WorkDay(), id);
    }

    /**
     * 是否可删除
     *
     * @param id 规则选项id
     * @return
     */
    @Override
    public boolean deleteable(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(option_id) FROM rel_rule_option WHERE option_id = ?");
        Integer count = mySQLDao.count(sb.toString(), id);
        if (null != count && 0 < count) {
            return false;
        }
        return true;
    }

    /**
     * 是否可批量删除
     *
     * @param id 规则选项id数组
     * @return
     */
    @Override
    public boolean deleteable(final String[] id) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < id.length; i++) {
            if (i != id.length - 1) {
                temp.append("'").append(id[i]).append("',");
            } else {
                temp.append("'").append(id[i]).append("'");
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(option_id) FROM rel_rule_option WHERE option_id IN(");
        sb.append(temp.toString()).append(")");
        Integer count = mySQLDao.count(sb.toString());
        if (null != count && 0 < count) {
            return false;
        }
        return true;
    }

    /**
     * 删除规则选项
     *
     * @param id 规则选项id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(final String id) {
        try {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            // 删除规则选项
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM options WHERE id = ?");
            flag1 = mySQLDao.update(sb.toString(), id);

            // 删除规则-规则选项
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_rule_option WHERE option_id = ?");
            flag2 = mySQLDao.update(sb.toString(), id);

            // 删除工作日表
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_workday WHERE option_id = ?");
            flag3 = mySQLDao.update(sb.toString(), id);

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3) {
                return true;
            }
        } catch (Exception e) {
            logger.error("删除规则选项异常: {}", e.getMessage());
        }
        throw new RuntimeException("删除规则选项异常");
    }

    /**
     * 批量删除规则选项
     *
     * @param id 规则选项id数组
     * @return
     */
    @Override
    public boolean batchRemove(final String[] id) {
        try {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            StringBuilder temp = new StringBuilder();
            for (int i = 0; i < id.length; i++) {
                if (i != id.length - 1) {
                    temp.append("'").append(id[i]).append("',");
                } else {
                    temp.append("'").append(id[i]).append("'");
                }
            }

            // 删除规则选项
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM options WHERE id IN(");
            sb.append(temp.toString()).append(")");
            flag1 = mySQLDao.update(sb.toString());

            // 删除规则-规则选项
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_rule_option WHERE option_id IN(");
            sb.append(temp.toString()).append(")");
            flag2 = mySQLDao.update(sb.toString(), id);

            // 删除工作日表
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_workday WHERE option_id IN(");
            sb.append(temp.toString()).append(")");
            flag3 = mySQLDao.update(sb.toString());

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3) {
                return true;
            }
        } catch (Exception e) {
            logger.error("批量删除规则选项异常: {}", e.getMessage());
        }
        throw new RuntimeException("批量删除规则选项异常");
    }

    /**
     * 解析字符串并返回二维数组
     *
     * @param section
     * @return
     */
    private String[][] parseArray(final String[] section) {
        String[][] result = new String[section.length][2];

        for (int i = 0; i < section.length; i++) {
            String[] temp = section[i].split("-");
            result[i][0] = temp[0];
            result[i][1] = temp[1];
        }
        return result;
    }

    /**
     * 解析保存星期时的参数列表
     *
     * @param week
     * @param duration
     * @param type
     * @param timeArr
     * @param createtime
     * @return
     */
    private Map<String, Object> getArgumentList(final Integer[] week, final Integer duration, final Integer type, final String[][] timeArr, final long createtime) {
        Object[] objects = null;
        List<String> idList = new ArrayList<>();
        List<Object[]> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        String[] ids = null;
        String id = "";

        for (int i = 0; i < week.length; i++) {
            if (null == timeArr) {
                objects = new Object[7];
                id = SystemUtil.uuid();
                objects[0] = id;
                objects[1] = week[i];
                objects[2] = duration;
                objects[3] = type;
                objects[4] = "00:00:00";
                objects[5] = "00:00:00";
                objects[6] = createtime;
                list.add(objects);
                idList.add(id);
            } else {
                for (int j = 0; j < timeArr.length; j++) {
                    objects = new Object[7];
                    id = SystemUtil.uuid();
                    objects[0] = id;
                    objects[1] = week[i];
                    objects[2] = duration;
                    objects[3] = type;
                    objects[4] = timeArr[j][0];
                    objects[5] = timeArr[j][1];
                    objects[6] = createtime;
                    list.add(objects);
                    idList.add(id);
                }
            }
        }
        map.put("list", list);
        // 将List转成数组
        ids = new String[idList.size()];
        idList.toArray(ids);
        map.put("ids", ids);
        return map;
    }

    /**
     * 解析保存日期时的参数列表
     *
     * @param dateday
     * @param duration
     * @param type
     * @param timeArr
     * @param createtime
     * @return
     */
    private Map<String, Object> getArgumentList(final String[] dateday, final Integer duration, final Integer type, final String[][] timeArr, final long createtime) {
        Object[] objects = null;
        List<String> idList = new ArrayList<>();
        List<Object[]> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        String[] ids = null;
        String id = "";

        for (int i = 0; i < dateday.length; i++) {
            if (null == timeArr) {
                objects = new Object[7];
                id = SystemUtil.uuid();
                objects[0] = id;
                objects[1] = dateday[i];
                objects[2] = duration;
                objects[3] = type;
                objects[4] = "00:00:00";
                objects[5] = "00:00:00";
                objects[6] = createtime;
                list.add(objects);
                idList.add(id);
            } else {
                for (int j = 0; j < timeArr.length; j++) {
                    objects = new Object[7];
                    id = SystemUtil.uuid();
                    objects[0] = id;
                    objects[1] = dateday[i];
                    objects[2] = duration;
                    objects[3] = type;
                    objects[4] = timeArr[j][0];
                    objects[5] = timeArr[j][1];
                    objects[6] = createtime;
                    list.add(objects);
                    idList.add(id);
                }
            }
        }
        map.put("list", list);
        // 将List转成数组
        ids = new String[idList.size()];
        idList.toArray(ids);
        map.put("ids", ids);
        return map;
    }

    /**
     * 返回参数列表
     *
     * @param optionid
     * @param ids
     * @return
     */
    private List<Object[]> getWorkArgumentList(final String optionid, final String[] ids) {
        Object[] objects = null;
        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < ids.length; i++) {
            objects = new Object[2];
            objects[0] = optionid;
            objects[1] = ids[i];
            list.add(objects);
        }
        return list;
    }
}
