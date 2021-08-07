package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 部门Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class Department extends BaseObject implements RowMapper<Department>, Serializable {
    private static final long serialVersionUID = 1477248769814214753L;

    private int id;
    private String name;
    private int create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public Department mapRow(ResultSet result, int row) throws SQLException {
        Department user = new Department();
        user.setId(result.getInt("id"));
        user.setName(result.getString("name"));
        user.setCreate_time(result.getInt("create_time"));
        return user;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d, \"name\":\"%s\", \"create_time\":%d}", id, name, create_time);
    }
}
