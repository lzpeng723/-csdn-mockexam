package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 打卡地点Entity
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
public class Address extends BaseObject implements RowMapper<Address>, Serializable {
    private static final long serialVersionUID = -5640142641356189192L;

    private int id;
    private String title;
    private String region;
    private String address;
    private int create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    @Override
    public Address mapRow(ResultSet result, int row) throws SQLException {
        Address address = new Address();
        address.setId(result.getInt("id"));
        address.setTitle(result.getString("title"));
        address.setRegion(result.getString("region"));
        address.setAddress(result.getString("address"));
        address.setCreate_time(result.getInt("create_time"));
        return address;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%d, \"title\":\"%s\", \"region\":\"%s\", \"address\":\"%s\", \"create_time\":%d}", id, title, region, address, create_time);
    }
}
