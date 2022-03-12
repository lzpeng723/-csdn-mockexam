package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 规则选项Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class Option extends BaseObject implements RowMapper<Option>, Serializable {
    private static final long serialVersionUID = -2982637404342972346L;

    private String id;
    private String title;
    private String description;
    private int type;
    private int create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public Option mapRow(ResultSet result, int row) throws SQLException {
        Option option = new Option();
        option.setId(result.getString("id"));
        option.setTitle(result.getString("title"));
        option.setDescription(result.getString("description"));
        option.setType(result.getInt("type"));
        option.setCreate_time(result.getInt("create_time"));
        return option;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"title\":\"%s\", \"description\":\"%s\", \"type\":%d, \"create_time\":%d}", id, title, description, type, create_time);
    }
}
