package net.csdn.ac.check.service;

import net.csdn.ac.check.entity.Address;
import net.csdn.ac.check.entity.Option;
import net.csdn.ac.check.entity.Rule;
import net.csdn.ac.check.entity.User;

import java.util.List;

/**
 * 规则Service接口
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public interface RuleService {
    /**
     * 新建规则
     *
     * @param title         规则标题
     * @param description   规则描述
     * @param optionid      规则选项id数组
     * @param addressid     打卡地点id数组
     * @return
     */
    public boolean add(final String title, final String description, final String[] optionid, final Integer[] addressid);

    /**
     * 编辑规则
     *
     * @param id            规则id
     * @param title         规则标题
     * @param description   规则描述
     * @param optionid      规则选项id数组
     * @param addressid     打卡地点id数组
     * @return
     */
    public boolean update(final String id, final String title, final String description, final String[] optionid, final Integer[] addressid);

    /**
     * 规则查询
     *
     * @param id            规则id
     * @return
     */
    public Rule queryRule(final String id);

    /**
     * 规则数量
     *
     * @return
     */
    public Integer count();

    /**
     * 规则列表
     *
     * @param start         起始页，从1开始
     * @param size          每页条数
     * @return
     */
    public List<Rule> queryList(final int start, final int size);

    /**
     * 规则数量
     *
     * @param title         规则名称
     * @return
     */
    public Integer count(final String title);

    /**
     * 规则列表
     *
     * @param title         规则名称
     * @param start         起始页，从1开始
     * @param size          每页条数
     * @return
     */
    public List<Rule> queryList(final String title, final int start, final int size);

    /**
     * 规则选项列表
     *
     * @param id            规则id
     * @return
     */
    public List<Option> queryOptionList(final String id);

    /**
     * 打卡地点列表
     *
     * @param id            规则id
     * @return
     */
    public List<Address> queryAddressList(final String id);

    /**
     * 已选择人员
     *
     * @param id            规则id
     * @return
     */
    public List<User> select(final String id);

    /**
     * 分配人员
     *
     * @param id            规则id
     * @param userid        人员id数组
     * @return
     */
    public boolean assign(final String id, final Integer[] userid);

    /**
     * 禁用启用规则
     *
     * @param id            规则id
     * @param status        状态，0：未使用；1：已使用；2：已禁用
     * @return
     */
    public boolean enabled(final String id, final Integer status);

    /**
     * 是否可删除
     *
     * @param id            规则id
     * @return
     */
    public boolean deleteable(final String id);

    /**
     * 是否可批量删除
     *
     * @param id            规则id数组
     * @return
     */
    public boolean deleteable(final String[] id);

    /**
     * 删除规则
     *
     * @param id            规则id
     * @return
     */
    public boolean remove(final String id);

    /**
     * 批量删除规则
     *
     * @param id            规则id数组
     * @return
     */
    public boolean batchRemove(final String[] id);
}
