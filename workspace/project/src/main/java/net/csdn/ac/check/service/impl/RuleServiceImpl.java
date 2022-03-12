package net.csdn.ac.check.service.impl;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.constant.Constant;
import net.csdn.ac.check.core.utils.SystemUtil;
import net.csdn.ac.check.entity.Address;
import net.csdn.ac.check.entity.Option;
import net.csdn.ac.check.entity.Rule;
import net.csdn.ac.check.entity.User;
import net.csdn.ac.check.core.dao.MySQLDao;
import net.csdn.ac.check.service.RuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 规则Service实现类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@Service("ruleService")
public class RuleServiceImpl extends BaseObject implements RuleService {
    @Resource
    private MySQLDao mySQLDao;

    /**
     * 新建规则
     *
     * @param title         规则标题
     * @param description   规则描述
     * @param optionid      规则选项id数组
     * @param addressid     打卡地点id数组
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean add(final String title, final String description, final String[] optionid, final Integer[] addressid) {
        try {
            String ruleid = SystemUtil.uuid();
            long createtime = System.currentTimeMillis() / 1000;
            int code1 = -1;
            int code2 = -1;
            int code3 = -1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            List<Object[]> list = null;

            // 创建规则
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO rule (id, title, description, create_time) VALUES (?, ?, ?, ?)");
            code1 = mySQLDao.create(sb.toString(), ruleid, title, description, createtime);
            flag1 = code1 == Constant.SUCCESS_CODE;

            // 保存规则-规则选项关联
            sb = new StringBuilder();
            sb.append("INSERT INTO rel_rule_option (rule_id, option_id, sort) VALUES (?, ?, ?)");
            list = getArgumentList(ruleid, optionid);
            code2 = mySQLDao.batch(sb.toString(), list);
            flag2 = code2 == Constant.SUCCESS_CODE;

            // 保存规则-规则打卡地点关联
            sb = new StringBuilder();
            sb.append("INSERT INTO rel_rule_address (rule_id, address_id) VALUES (?, ?)");
            list = getArgumentList(ruleid, addressid);
            code3 = mySQLDao.batch(sb.toString(), list);
            flag3 = code3 == Constant.SUCCESS_CODE;

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3) {
                return true;
            }
        } catch (Exception e) {
            logger.error("新建规则异常: {}", e.getMessage());
        }
        throw new RuntimeException("新建规则异常");
    }

    /**
     * 编辑规则
     *
     * @param id            规则id
     * @param title         规则名称
     * @param description   规则描述
     * @param optionid      规则选项id数组
     * @param addressid     打卡地点id数组
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(final String id, final String title, final String description, final String[] optionid, final Integer[] addressid) {
        try {
            int code1 = -1;
            int code2 = -1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            boolean flag4 = false;
            boolean flag5 = false;
            List<Object[]> list = null;

            // 更新规则内容
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE rule SET title = ?, description = ? WHERE id = ?");
            flag1 = mySQLDao.update(sb.toString(), title, description, id);

            // 先删除规则-规则选项关联
            sb = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            sb.append("DELETE FROM rel_rule_option WHERE rule_id = ?");
            flag2 = mySQLDao.update(sb.toString(), id);

            // 再保存规则-规则选项关联
            sb = new StringBuilder();
            sb.append("INSERT INTO rel_rule_option (rule_id, option_id, sort) VALUES (?, ?, ?)");
            list = getArgumentList(id, optionid);
            code1 = mySQLDao.batch(sb.toString(), list);
            flag3 = code1 == Constant.SUCCESS_CODE;

            // 先删除规则-规则打卡地点关联
            sb = new StringBuilder();
            temp = new StringBuilder();
            sb.append("DELETE FROM rel_rule_address WHERE rule_id = ?");
            flag4 = mySQLDao.update(sb.toString(), id);

            // 再保存规则-规则打卡地点关联
            sb = new StringBuilder();
            sb.append("INSERT INTO rel_rule_address (rule_id, address_id) VALUES (?, ?)");
            list = getArgumentList(id, addressid);
            code2 = mySQLDao.batch(sb.toString(), list);
            flag5 = code2 == Constant.SUCCESS_CODE;

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3 && flag4 && flag5) {
                return true;
            }
        } catch (Exception e) {
            logger.error("编辑规则异常: {}", e.getMessage());
        }
        throw new RuntimeException("编辑规则异常");
    }

    /**
     * 规则查询
     *
     * @param id            规则id
     * @return
     */
    @Override
    public Rule queryRule(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, description, influenced, status, create_time FROM rule WHERE id = ?");
        return (Rule) mySQLDao.findOne(sb.toString(), new Rule(), id);
    }

    /**
     * 规则数量
     *
     * @return
     */
    @Override
    public Integer count() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(r.id) FROM rule AS r, rel_rule_address AS ra, address AS a WHERE r.id = ra.rule_id AND ra.address_id = a.id");
        return mySQLDao.count(sb.toString());
    }

    /**
     * 规则数量
     *
     * @param start         起始页，从1开始
     * @param size          每页条数
     * @return
     */
    @Override
    public List<Rule> queryList(final int start, final int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT r.id, r.title, r.description, r.influenced, r.status, r.create_time ");
        sb.append("FROM rule AS r, rel_rule_address AS ra, address AS a WHERE r.id = ra.rule_id AND ra.address_id = a.id ORDER BY r.create_time DESC LIMIT ");
        sb.append((start - 1) * size).append(", ").append(size);
        return (List<Rule>) mySQLDao.find(sb.toString(), new Rule());
    }

    /**
     * 规则数量
     *
     * @param title         规则名称
     * @return
     */
    @Override
    public Integer count(final String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) FROM rule WHERE title LIKE '%").append(title).append("%'");
        return mySQLDao.count(sb.toString());
    }

    /**
     * 规则列表
     *
     * @param title         规则名称
     * @param start         起始页，从1开始
     * @param size          每页条数
     * @return
     */
    @Override
    public List<Rule> queryList(final String title, final int start, final int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, description, influenced, status, create_time FROM rule WHERE title LIKE '%").append(title).append("%' ");
        sb.append("ORDER BY create_time DESC LIMIT ").append((start - 1) * size).append(", ").append(size);
        return (List<Rule>) mySQLDao.find(sb.toString(), new Rule());
    }

    /**
     * 规则选项列表
     *
     * @param id            规则id
     * @return
     */
    @Override
    public List<Option> queryOptionList(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT o.id, o.title, o.description, o.type, o.create_time FROM rule AS r, rel_rule_option AS ro, options AS o ");
        sb.append("WHERE r.id = ro.rule_id AND ro.option_id = o.id AND r.id = ? ORDER BY ro.sort");
        return (List<Option>) mySQLDao.find(sb.toString(), new Option(), id);
    }

    /**
     * 打卡地点列表
     *
     * @param id            规则id
     * @return
     */
    @Override
    public List<Address> queryAddressList(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a.id, a.title, a.region, a.address, a.create_time FROM rule AS r, rel_rule_address AS ra, address AS a ");
        sb.append("WHERE r.id = ra.rule_id AND ra.address_id = a.id AND r.id = ?");
        return (List<Address>) mySQLDao.find(sb.toString(), new Address(), id);
    }

    /**
     * 已选择人员
     *
     * @param id            规则id
     * @return
     */
    @Override
    public List<User> select(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.id, u.real_name, u.gender, u.birthday, u.create_time FROM user AS u, rel_user_rule AS ur ");
        sb.append("WHERE u.id = ur.user_id AND ur.rule_id = ?");
        return (List<User>) mySQLDao.find(sb.toString(), new User(), id);
    }

    /**
     * 分配人员
     *
     * @param id            规则id
     * @param userid        人员id数组
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean assign(final String id, final Integer[] userid) {
        try {
            int code = -1;
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;
            List<Object[]> list = null;

            // 先删除规则人员
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM rel_user_rule WHERE rule_id = ?");
            flag1 = mySQLDao.update(sb.toString(), id);

            // 再分配人员
            sb = new StringBuilder();
            sb.append("INSERT INTO rel_user_rule (rule_id, user_id) VALUES (?, ?)");
            list = getArgumentList(id, userid);
            code = mySQLDao.batch(sb.toString(), list);
            flag2 = code == Constant.SUCCESS_CODE;

            // 更新受影响人数和状态
            sb = new StringBuilder();
            if (null != userid && 0 < userid.length) {
                sb.append("UPDATE rule SET influenced = ?, status = 1 WHERE id = ?");
            } else {
                sb.append("UPDATE rule SET influenced = ?, status = 0 WHERE id = ?");
            }
            flag3 = mySQLDao.update(sb.toString(), userid.length, id);

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3) {
                return true;
            }
        } catch (Exception e) {
            logger.error("新建规则异常: {}", e.getMessage());
        }
        throw new RuntimeException("新建规则异常");
    }

    /**
     * 禁用启用规则
     *
     * @param id            规则id
     * @param status        状态，0：未使用；1：已使用；2：已禁用
     * @return
     */
    @Override
    public boolean enabled(final String id, final Integer status) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE rule SET status = ? WHERE id = ?");
            return mySQLDao.update(sb.toString(), status, id);
        } catch (Exception e) {
            logger.error("禁用启用规则异常: {}", e.getMessage());
        }
        return false;
    }

    /**
     * 是否可删除
     *
     * @param id        规则id
     * @return
     */
    @Override
    public boolean deleteable(final String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(rule_id) FROM rel_user_rule WHERE rule_id = ?");
        Integer count = mySQLDao.count(sb.toString(), id);
        if (null != count && 0 < count) {
            return false;
        }
        return true;
    }

    /**
     * 是否可批量删除
     *
     * @param id        规则id数组
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
        sb.append("SELECT COUNT(rule_id) FROM rel_user_rule WHERE rule_id IN(");
        sb.append(temp.toString()).append(")");
        Integer count = mySQLDao.count(sb.toString());
        if (null != count && 0 < count) {
            return false;
        }
        return true;
    }

    /**
     * 删除规则
     *
     * @param id            规则id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(final String id) {
        try {
            boolean flag1 = false;
            boolean flag2 = false;
            boolean flag3 = false;

            // 删除规则
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM rule WHERE id = ?");
            flag1 = mySQLDao.update(sb.toString(), id);

            // 删除用户规则
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_user_rule WHERE rule_id = ?");
            flag2 = mySQLDao.update(sb.toString(), id);

            // 删除地点规则
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_rule_address WHERE rule_id = ?");
            flag3 = mySQLDao.update(sb.toString(), id);

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3) {
                return true;
            }
        } catch (Exception e) {
            logger.error("删除规则异常: {}", e.getMessage());
        }
        throw new RuntimeException("删除规则异常");
    }

    /**
     * 批量删除规则
     *
     * @param id            规则id数组
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

            // 删除规则
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM rule WHERE id IN(");
            sb.append(temp.toString()).append(")");
            flag1 = mySQLDao.update(sb.toString());

            // 删除用户规则
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_user_rule WHERE rule_id IN(");
            sb.append(temp.toString()).append(")");
            flag2 = mySQLDao.update(sb.toString(), id);

            // 删除地点规则
            sb = new StringBuilder();
            sb.append("DELETE FROM rel_rule_address WHERE rule_id IN(");
            sb.append(temp.toString()).append(")");
            flag3 = mySQLDao.update(sb.toString());

            // 全部成功才认为成功
            if (flag1 && flag2 && flag3) {
                return true;
            }
        } catch (Exception e) {
            logger.error("批量删除规则异常: {}", e.getMessage());
        }
        throw new RuntimeException("批量删除规则异常");
    }

    /**
     * 解析参数列表
     *
     * @param ruleid    规则id
     * @param optionid  字符串id数组
     * @return
     */
    private List<Object[]> getArgumentList(final String ruleid, final String[] optionid) {
        Object[] objects = null;
        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < optionid.length; i++) {
            objects = new Object[3];
            objects[0] = ruleid;
            objects[1] = optionid[i];
            objects[2] = i;
            list.add(objects);
        }
        return list;
    }

    /**
     * 解析参数列表
     *
     * @param ruleid    规则id
     * @param addressid 整数id数组
     * @return
     */
    private List<Object[]> getArgumentList(final String ruleid, final Integer[] addressid) {
        Object[] objects = null;
        List<Object[]> list = new ArrayList<>();

        for (int i = 0; i < addressid.length; i++) {
            objects = new Object[2];
            objects[0] = ruleid;
            objects[1] = addressid[i];
            list.add(objects);
        }
        return list;
    }
}
