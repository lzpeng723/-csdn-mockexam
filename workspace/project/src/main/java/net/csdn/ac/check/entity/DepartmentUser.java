package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 部门用户Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class DepartmentUser extends BaseObject implements RowMapper<DepartmentUser>, Serializable {
    private static final long serialVersionUID = 5877432848454214132L;

    private int dept_id;
    private String name;
    private int user_id;
    private String real_name;

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    @Override
    public DepartmentUser mapRow(ResultSet result, int row) throws SQLException {
        DepartmentUser du = new DepartmentUser();
        du.setDept_id(result.getInt("dept_id"));
        du.setName(result.getString("name"));
        du.setUser_id(result.getInt("user_id"));
        du.setReal_name(result.getString("real_name"));
        return du;
    }

    @Override
    public String toString() {
        return String.format("{\"dept_id\":%d, \"name\":\"%s\", \"user_id\":%d, \"real_name\":%d}", dept_id, name, user_id, real_name);
    }
}
