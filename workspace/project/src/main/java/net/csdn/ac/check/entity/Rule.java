package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 规则Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class Rule extends BaseObject implements RowMapper<Rule>, Serializable {
    private static final long serialVersionUID = -6088196464922167263L;

    private String id;
    private String title;
    private String description;
    private int influenced;
    private int status;
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

    public int getInfluenced() {
        return influenced;
    }

    public void setInfluenced(int influenced) {
        this.influenced = influenced;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public Rule mapRow(ResultSet result, int row) throws SQLException {
        Rule rule = new Rule();
        rule.setId(result.getString("id"));
        rule.setTitle(result.getString("title"));
        rule.setDescription(result.getString("description"));
        rule.setInfluenced(result.getInt("influenced"));
        rule.setStatus(result.getInt("status"));
        rule.setCreate_time(result.getInt("create_time"));
        return rule;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":\"%s\", \"title\":\"%s\", \"description\":\"%s\", \"influenced\":%d, \"status\":%d, " +
                "\"create_time\":%d}", id, title, description, influenced, status, create_time);
    }
}
