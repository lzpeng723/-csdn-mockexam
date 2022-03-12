package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.utils.DateUtil;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 管理员用户Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class AdminUser extends BaseObject implements RowMapper<AdminUser>, Serializable {
    private static final long serialVersionUID = 2205003165543903841L;

    private int id;
    private int uid;
    private String login_name;
    private String password;
    private String real_name;
    private int gender;
    private Date birthday;
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

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
    public AdminUser mapRow(ResultSet result, int row) throws SQLException {
        AdminUser admin = new AdminUser();
        admin.setId(result.getInt("id"));
        admin.setUid(result.getInt("uid"));
        admin.setLogin_name(result.getString("login_name"));
        admin.setPassword(result.getString("password"));
        admin.setReal_name(result.getString("real_name"));
        admin.setGender(result.getInt("gender"));
        admin.setBirthday(result.getTimestamp("birthday"));
        admin.setSalt(result.getString("salt"));
        admin.setCreate_time(result.getInt("create_time"));
        return admin;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d, \"uid\":%d, \"login_name\":\"%s\", \"password\":\"%s\", \"real_name\":\"%s\", " +
                        "\"gender\":%d, \"birthday\":\"%s\", \"salt\":\"%s\", \"create_time\":%d}",
                id, uid, login_name, password, real_name, gender, DateUtil.parseDay(birthday), salt, create_time);
    }
}
