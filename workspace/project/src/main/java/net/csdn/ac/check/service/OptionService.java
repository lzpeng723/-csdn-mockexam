package net.csdn.ac.check.service;

import net.csdn.ac.check.entity.WorkDay;
import net.csdn.ac.check.entity.Option;
import java.util.List;

/**
 * 规则选项Service接口
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public interface OptionService {
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
    public String add(final String title, final String description, final Integer timeType, final Integer[] week, final String[] dateday, final Integer type, final String[] section, final Integer timeLen);

    /**
     * 规则选项数量
     *
     * @return
     */
    public Integer count();

    /**
     * 规则选项列表
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    public List<Option> queryList(final int start, final int size);

    /**
     * 规则选项数量
     *
     * @param title 查询内容
     * @param type  规则类型，1：星期，2：日期
     * @return
     */
    public Integer count(final String title, final Integer type);

    /**
     * 规则选项列表
     *
     * @param title 查询内容
     * @param type  规则类型，1：星期，2：日期
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    public List<Option> queryList(final String title, final Integer type, final int start, final int size);

    /**
     * 查询规则选项
     *
     * @param id 规则选项id
     * @return
     */
    public Option queryOne(final String id);

    /**
     * 规则选项详情
     *
     * @param id 规则选项id
     * @return
     */
    public List<WorkDay> WeekdayDetails(final String id);

    /**
     * 是否可删除
     *
     * @param id 规则选项id
     * @return
     */
    public boolean deleteable(final String id);

    /**
     * 是否可批量删除
     *
     * @param id 规则选项id数组
     * @return
     */
    public boolean deleteable(final String[] id);

    /**
     * 规则选项详情
     *
     * @param id 规则选项id
     * @return
     */
    public List<WorkDay> DatedayDetails(final String id);

    /**
     * 删除规则选项
     *
     * @param id 规则选项id
     * @return
     */
    public boolean remove(final String id);

    /**
     * 批量删除规则选项
     *
     * @param id 规则选项id数组
     * @return
     */
    public boolean batchRemove(final String[] id);
}
