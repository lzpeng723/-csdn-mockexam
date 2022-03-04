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

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:34
 */
@Data
@Entity
@ApiModel("用户")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends BaseEntity {

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    @Column(columnDefinition = "varchar(32) COMMENT '姓名'")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private Boolean sex;

    /**
     * 姓名
     */
    @ApiModelProperty("手机号")
    @NotBlank(message = "手机号不能为空")
    @Column(columnDefinition = "varchar(32) COMMENT '手机号'")
    private String phone;


    /**
     * 姓名
     */
    @Past(message = "生日必须是过去的时间")
    @ApiModelProperty("生日")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @NotBlank(message = "生日不能为空")
    private LocalDateTime birthday;

    /**
     * 教育经历
     */
    @OneToMany(targetEntity = Education.class, cascade = CascadeType.ALL)
    @ApiModelProperty(value = "教育经历", hidden = true)
    @JoinColumn(name = "user_id", columnDefinition = "varchar(255) COMMENT '用户id'")
    private List<Education> educationList = new ArrayList<>();

    /**
     * 工作经历
     */
    @OneToMany(targetEntity = Job.class, cascade = CascadeType.ALL)
    @ApiModelProperty(value = "工作经历", hidden = true)
    @JoinColumn(name = "user_id", columnDefinition = "varchar(255) COMMENT '用户id'")
    private List<Job> jobList = new ArrayList<>();

    /**
     * 项目经验
     */
    @OneToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
    @ApiModelProperty(value = "项目经验", hidden = true)
    @JoinColumn(name = "user_id", columnDefinition = "varchar(255) COMMENT '用户id'")
    private List<Project> projectList = new ArrayList<>();


}
