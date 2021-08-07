package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 规则选项详情Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class WorkDay extends BaseObject implements RowMapper<WorkDay>, Serializable {
    private static final long serialVersionUID = 8640377406861024076L;

    private String weekday;
    private String dateday;
    private int duration;
    private int type;
    private String start_time;
    private String end_time;

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getDateday() {
        return dateday;
    }

    public void setDateday(String dateday) {
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

    @Override
    public WorkDay mapRow(ResultSet result, int row) throws SQLException {
        WorkDay details = new WorkDay();
        details.setWeekday(result.getString("weekday"));
        details.setDateday(result.getString("dateday"));
        details.setDuration(result.getInt("duration"));
        details.setType(result.getInt("type"));
        details.setStart_time(result.getString("start_time"));
        details.setEnd_time(result.getString("end_time"));
        return details;
    }

    @Override
    public String toString() {
        return String.format("{\"weekday\":\"%s\", \"dateday\":\"%s\", \"duration\":%d, \"type\":%d, \"start_time\":\"%s\", \"end_time\":\"%s\"}",
                weekday, dateday, duration, type, start_time, end_time);
    }
}
