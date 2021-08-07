package net.csdn.ac.check.service.impl;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.entity.AdminLogin;
import net.csdn.ac.check.entity.AdminUser;
import net.csdn.ac.check.entity.Department;
import net.csdn.ac.check.entity.DepartmentUser;
import net.csdn.ac.check.service.UserService;
import net.csdn.ac.check.core.dao.MySQLDao;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 用户Service实现类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@Service("userService")
public class UserServiceImpl extends BaseObject implements UserService {
    @Resource
    private MySQLDao mySQLDao;
    @Resource
    private LoadingCache<String, String> cache;

    /**
     * 保存缓存数据
     *
     * @param key       验证码key
     * @param value     验证码value
     */
    @Override
    public void setCache(final String key, final String value) {
        cache.put(key, value);
    }

    /**
     * 获取缓存数据
     *
     * @param key       验证码key
     * @return
     */
    @Override
    public String getCache(final String key) {
        return cache.get(key);
    }

    /**
     * 清除缓存数据
     *
     * @param key       验证码key
     * @return
     */
    @Override
    public void clearCache(final String key) {
        cache.invalidate(key);
    }

    /**
     * 查询用户
     *
     * @param loginName 登录名
     * @return
     */
    @Override
    public AdminUser queryByLoginName(final String loginName) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT a.id, a.uid, a.login_name, a.password, u.real_name, u.gender, u.birthday, a.salt, a.create_time ");
        sb.append("FROM admin_login AS a, user AS u WHERE a.uid = u.id AND a.login_name = ?");
        return (AdminUser) mySQLDao.findOne(sb.toString(), new AdminUser(), loginName);
    }

    /**
     * 查询登录用户
     *
     * @param id         用户id
     * @return
     */
    @Override
    public AdminLogin queryAdminLogin(final Integer id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, uid, login_name, password, salt, create_time FROM admin_login WHERE id = ?");
        return (AdminLogin) mySQLDao.findOne(sb.toString(), new AdminLogin(), id);
    }

    /**
     * 选择人员
     *
     * @return
     */
    @Override
    public List<DepartmentUser> select() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT du.dept_id, d.name, du.user_id, u.real_name FROM department AS d, rel_department_user AS du, user AS u ");
        sb.append("WHERE d.id = du.dept_id AND du.user_id = u.id");
        return (List<DepartmentUser>) mySQLDao.find(sb.toString(), new DepartmentUser());
    }

    /**
     * 部门列表
     *
     * @return
     */
    @Override
    public List<Department> deptList() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT id, name, create_time FROM department");
        return (List<Department>) mySQLDao.find(sb.toString(), new Department());
    }
}
