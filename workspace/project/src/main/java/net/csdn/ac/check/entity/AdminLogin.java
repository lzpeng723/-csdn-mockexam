package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 管理员登录Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class AdminLogin extends BaseObject implements RowMapper<AdminLogin>, Serializable {
    private static final long serialVersionUID = -6638604760959454473L;

    private int id;
    private int uid;
    private String login_name;
    private String password;
    private String salt;
    private int create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public AdminLogin mapRow(ResultSet result, int row) throws SQLException {
        AdminLogin admin = new AdminLogin();
        admin.setId(result.getInt("id"));
        admin.setUid(result.getInt("uid"));
        admin.setLogin_name(result.getString("login_name"));
        admin.setPassword(result.getString("password"));
        admin.setSalt(result.getString("salt"));
        admin.setCreate_time(result.getInt("create_time"));
        return admin;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d, \"uid\":%d, \"login_name\":\"%s\", \"password\":\"%s\", \"salt\":\"%s\", \"create_time\":%d}",
                id, uid, login_name, password, salt, create_time);
    }
}
