package net.csdn.ac.tasking.springboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.csdn.ac.tasking.springboot.config.GenerateEntityIdListener;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 多数据源 https://www.jianshu.com/p/9f812e651319
 * 列注释 http://www.majunwei.com/view/201707241152140494.html
 * 注解@JsonFormat 主要是后台到前台的时间格式的转换
 * 注解@DateTimeFormat 主要是前后到后台的时间格式的转换
 * JPA的CascadeType的解释: https://www.jianshu.com/p/ae07c9f147bc
 *
 * @DynamicInsert Insert 时不插入 null, 可以使数据库默认值生效
 * 要使用包装类型变量，不要使用基本类型变量
 * 基础的关系型数据库实体
 * @date: 2020/2/1
 * @time: 21:51
 * @author: lzpeng
 */
@Data
@ApiModel("基础实体")
@DynamicInsert
@MappedSuperclass
@EnableJpaAuditing
@EntityListeners({AuditingEntityListener.class, GenerateEntityIdListener.class})
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class BaseEntity implements Serializable {

    /**
     * 序列化id
     */
    @Transient
    protected static final long serialVersionUID = 1L;

    @Id
    @ApiModelProperty(value = "主键ID", hidden = true)
    @Column(columnDefinition = "varchar(255) COMMENT 'id 主键'", updatable = false)
    private String id;

    /**
     * @time
     */
    @CreatedDate
    @ApiModelProperty(value = "创建时间", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "datetime COMMENT '创建时间'", updatable = false)
    private LocalDateTime createTime;
    /**
     * 最后修改时间
     */
    @LastModifiedDate
    @ApiModelProperty(value = "修改时间", hidden = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(columnDefinition = "datetime COMMENT '修改时间'")
    private LocalDateTime updateTime;

    /**
     * @author
     */
    @CreatedBy
    @ApiModelProperty(value = "创建人", hidden = true)
    @Column(columnDefinition = "varchar(255) COMMENT '创建人'", updatable = false)
    private String createBy;
    /**
     * 最后修改者
     */
    @LastModifiedBy
    @ApiModelProperty(value = "修改人", hidden = true)
    @Column(columnDefinition = "varchar(255) COMMENT '修改人'")
    private String updateBy;


}