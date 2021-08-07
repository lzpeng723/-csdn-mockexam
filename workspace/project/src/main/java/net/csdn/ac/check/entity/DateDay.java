package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import net.csdn.ac.check.core.utils.DateUtil;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 日期Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class DateDay extends BaseObject implements RowMapper<DateDay>, Serializable {
    private static final long serialVersionUID = 2959617426107307034L;

    private String id;
    private Date dateday;
    private int duration;
    private int type;
    private String start_time;
    private String end_time;
    private int create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDateday() {
        return dateday;
    }

    public void setDateday(Date dateday) {
        this.dateday = dateday;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public DateDay mapRow(ResultSet result, int row) throws SQLException {
        DateDay dateDay = new DateDay();
        dateDay.setId(result.getString("id"));
        dateDay.setDateday(result.getTimestamp("dateday"));
        dateDay.setDuration(result.getInt("duration"));
        dateDay.setType(result.getInt("type"));
        dateDay.setStart_time(result.getString("start_time"));
        dateDay.setEnd_time(result.getString("end_time"));
        dateDay.setCreate_time(result.getInt("create_time"));
        return dateDay;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"dateday\":\"%s\", \"duration\":%d, \"type\":%d, \"start_time\":\"%s\", \"end_time\":\"%s\", \"create_time\":%d}",
                id, DateUtil.day(dateday), duration, type, start_time, end_time, create_time);
    }
}
