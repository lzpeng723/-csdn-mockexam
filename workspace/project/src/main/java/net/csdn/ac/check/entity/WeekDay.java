package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 星期Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class WeekDay extends BaseObject implements RowMapper<WeekDay>, Serializable {
    private static final long serialVersionUID = 7182008303017644553L;

    private String id;
    private int weekday;
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

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
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
    public WeekDay mapRow(ResultSet result, int row) throws SQLException {
        WeekDay weekDay = new WeekDay();
        weekDay.setId(result.getString("id"));
        weekDay.setWeekday(result.getInt("weekday"));
        weekDay.setDuration(result.getInt("duration"));
        weekDay.setType(result.getInt("type"));
        weekDay.setStart_time(result.getString("start_time"));
        weekDay.setEnd_time(result.getString("end_time"));
        weekDay.setCreate_time(result.getInt("create_time"));
        return weekDay;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"weekday\":%d, \"duration\":%d, \"type\":%d, \"start_time\":\"%s\", \"end_time\":\"%s\", \"create_time\":%d}",
                id, weekday, duration, type, start_time, end_time, create_time);
    }
}
