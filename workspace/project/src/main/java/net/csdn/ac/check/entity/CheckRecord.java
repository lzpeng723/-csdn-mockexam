package net.csdn.ac.check.entity;

import net.csdn.ac.check.core.base.BaseObject;
import org.springframework.jdbc.core.RowMapper;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 打卡记录Entity
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/5 0:02
 */
public class CheckRecord extends BaseObject implements RowMapper<CheckRecord>, Serializable {
    private static final long serialVersionUID = -5640142641356189192L;

    private Integer id;
    private Integer userId;
    private Integer checkTime;
    private Integer addressId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Integer checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public CheckRecord mapRow(ResultSet result, int row) throws SQLException {
        CheckRecord checkRecord = new CheckRecord();
        checkRecord.setId(result.getInt("id"));
        checkRecord.setUserId(result.getInt("user_id"));
        checkRecord.setCheckTime(result.getInt("check_time"));
        checkRecord.setAddressId(result.getInt("address_id"));
        return checkRecord;
    }

    @Override
    public String toString() {
        return "CheckRecord{" +
                "id=" + id +
                ", userId=" + userId +
                ", checkTime=" + checkTime +
                ", addressId=" + addressId +
                '}';
    }
}
