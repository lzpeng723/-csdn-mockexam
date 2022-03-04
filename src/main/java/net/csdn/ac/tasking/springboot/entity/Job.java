package net.csdn.ac.tasking.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * 工作经历
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:56
 */
@Data
@Entity
@ApiModel("工作经历")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Job extends BaseEntity {


    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "datetime COMMENT '开始时间'", updatable = false)
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "datetime COMMENT '结束时间'")
    private LocalDateTime endTime;


    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @Column(columnDefinition = "varchar(64) COMMENT '公司名称'")
    private String companyName;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    @Column(columnDefinition = "varchar(32) COMMENT '岗位名称'")
    private String positionName;

    /**
     * 岗位职责
     */
    @ApiModelProperty(value = "岗位职责")
    @Column(columnDefinition = "varchar(255) COMMENT '岗位职责'")
    private String positionResponsibility;

}
