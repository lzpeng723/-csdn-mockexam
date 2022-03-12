package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.utils.DateUtil;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 用户Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class User extends BaseObject implements RowMapper<User>, Serializable {
    private static final long serialVersionUID = -701953753138409466L;

    private int id;
    private String real_name;
    private int gender;
    private Date birthday;
    private int create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public User mapRow(ResultSet result, int row) throws SQLException {
        User user = new User();
        user.setId(result.getInt("id"));
        user.setReal_name(result.getString("real_name"));
        user.setGender(result.getInt("gender"));
        user.setBirthday(result.getTimestamp("birthday"));
        user.setCreate_time(result.getInt("create_time"));
        return user;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d, \"real_name\":\"%s\", \"gender\":%d, \"birthday\":\"%s\", \"create_time\":%d}",
                id, real_name, gender, DateUtil.parseDay(birthday), create_time);
    }
}
