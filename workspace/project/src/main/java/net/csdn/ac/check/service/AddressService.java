package net.csdn.ac.check.service;

import net.csdn.ac.check.entity.Address;

import java.util.List;

/**
 * 地点Service接口
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public interface AddressService {
    /**
     * 打卡地点数量
     *
     * @return
     */
    public Integer count();

    /**
     * 打卡地点数量
     *
     * @param title
     * @return
     */
    public Integer count(final String title);

    /**
     * 打卡地点列表
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    public List<Address> queryList(final int start, final int size);

    /**
     * 打卡地点查询
     *
     * @param title 查询内容
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    public List<Address> queryByTitle(final String title, int start, int size);
}
