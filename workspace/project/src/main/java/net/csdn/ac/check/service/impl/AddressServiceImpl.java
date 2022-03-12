package net.csdn.ac.check.service.impl;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.dao.MySQLDao;
import net.csdn.ac.check.entity.Address;
import net.csdn.ac.check.service.AddressService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 地点Service实现类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@Service("addressService")
public class AddressServiceImpl extends BaseObject implements AddressService {
    @Resource
    private MySQLDao mySQLDao;

    /**
     * 打卡地点数量
     *
     * @return
     */
    @Override
    public Integer count() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) FROM address");
        try {
            return mySQLDao.count(sb.toString());
        } catch (DataAccessException e) {
            logger.error("打卡地点数量异常：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 打卡地点数量
     *
     * @param title
     * @return
     */
    @Override
    public Integer count(final String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(id) FROM address WHERE title LIKE '%").append(title).append("%'");
        try {
            return mySQLDao.count(sb.toString());
        } catch (DataAccessException e) {
            logger.error("打卡地点数量异常：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 打卡地点列表
     *
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @Override
    public List<Address> queryList(int start, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, region, address, create_time FROM address ORDER BY create_time DESC LIMIT ").append((start - 1) * size).append(", ").append(size);

        try {
            List<Address> list = mySQLDao.find(sb.toString(), new Address());
            if (null != list && !list.isEmpty()) {
                return list;
            }
        } catch (DataAccessException e) {
            logger.error("打卡地点列表异常：{}", e.getMessage());
        }
        return null;
    }

    /**
     * 打卡地点查询
     *
     * @param title 查询内容
     * @param start 起始页，从1开始
     * @param size  每页条数
     * @return
     */
    @Override
    public List<Address> queryByTitle(final String title, int start, int size) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, title, region, address, create_time FROM address WHERE title LIKE '%").append(title);
        sb.append("%' ORDER BY create_time DESC LIMIT ").append((start - 1) * size).append(", ").append(size);

        try {
            List<Address> list = mySQLDao.find(sb.toString(), new Address());
            if (null != list && !list.isEmpty()) {
                return list;
            }
        } catch (DataAccessException e) {
            logger.error("查询打卡地点异常：{}", e.getMessage());
        }
        return null;
    }
}
