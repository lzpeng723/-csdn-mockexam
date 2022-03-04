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
 * 项目经验
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:56
 */
@Data
@Entity
@ApiModel("项目经验")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Project extends BaseEntity {


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
     * 项目名称
     */
    @ApiModelProperty(value = "项目名称")
    @Column(columnDefinition = "varchar(64) COMMENT '项目名称'")
    private String projectName;

    /**
     * 项目介绍
     */
    @ApiModelProperty(value = "项目介绍")
    @Column(columnDefinition = "varchar(255) COMMENT '项目介绍'")
    private String projectDescription;

    /**
     * 你的成就
     */
    @ApiModelProperty(value = "你的成就")
    @Column(columnDefinition = "varchar(255) COMMENT '你的成就'")
    private String achievement;

}
