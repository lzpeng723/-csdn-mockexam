package net.csdn.ac.check.service;

import net.csdn.ac.check.entity.AdminLogin;
import net.csdn.ac.check.entity.AdminUser;
import net.csdn.ac.check.entity.Department;
import net.csdn.ac.check.entity.DepartmentUser;
import java.util.List;

/**
 * 用户Service接口
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public interface UserService {
    /**
     * 保存缓存数据
     *
     * @param key       验证码key
     * @param key       验证码value
     */
    public void setCache(final String key, final String value);

    /**
     * 获取缓存数据
     *
     * @param key       验证码key
     * @return
     */
    public String getCache(final String key);

    /**
     * 清除缓存数据
     *
     * @param key       验证码key
     * @return
     */
    public void clearCache(final String key);

    /**
     * 查询用户
     *
     * @param loginName 登录名
     * @return
     */
    public AdminUser queryByLoginName(final String loginName);

    /**
     * 查询登录用户
     *
     * @param id         用户id
     * @return
     */
    public AdminLogin queryAdminLogin(final Integer id);

    /**
     * 选择人员
     *
     * @return
     */
    public List<DepartmentUser> select();

    /**
     * 部门列表
     *
     * @return
     */
    public List<Department> deptList();
}
