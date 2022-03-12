package net.csdn.ac.check.param;

import net.csdn.ac.check.core.validation.PastDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Arrays;

/**
 * 打卡记录参数
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/5 0:10
 */
public class CheckRecordParam {

    @PastDate(message = "必须是过去的日期")
    @NotBlank(message = "请输入开始日期")
    private String startDate;
    @PastDate(message = "必须是过去的日期")
    @NotBlank(message = "请输入结束日期")
    private String endDate;
    private String[] departId;
    @Positive(message = "页数必须大于 0 ")
    private Integer start;
    @Positive(message = "每页数据必须大于 0 ")
    private Integer size;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getDepartId() {
        return departId;
    }

    public void setDepartId(String[] departId) {
        this.departId = departId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCacheKey(){
        return String.join("##", startDate, endDate, Arrays.toString(departId), String.valueOf(start), String.valueOf(size));
    }
}
