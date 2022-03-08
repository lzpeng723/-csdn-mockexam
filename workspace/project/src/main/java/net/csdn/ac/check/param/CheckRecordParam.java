package net.csdn.ac.check.param;

/**
 * 打卡记录参数
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/5 0:10
 */
public class CheckRecordParam {

    private String startDate;
    private String endDate;
    private String[] departId;
    private Integer start;
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
}
