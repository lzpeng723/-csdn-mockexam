package net.csdn.ac.c4.exam.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Table;
import javax.persistence.*;

/**
 * 字段
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/16 0:27
 */
@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "table_field_name", columnNames = {"tableGuid", "field"}))
@ApiModel("字段")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Field extends BaseEntity {


    @Id
    @ApiModelProperty(value = "主键ID", hidden = true)
    @Column(columnDefinition = "varchar(255) NOT NULL COMMENT 'id 主键'", updatable = false)
    private String fieldGuid;

    /**
     * 字段名
     */
    @ApiModelProperty("字段名")
    @Column(columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '字段名'")
    private String field;

    /**
     * 字段别名
     */
    @ApiModelProperty("字段别名")
    @Column(columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '字段别名'")
    private String fieldName;

    /**
     * 字段类型
     */
    @ApiModelProperty("字段类型")
    @Column(name = "`type`", columnDefinition = "varchar(32) NOT NULL COMMENT '字段类型'")
    private String type;

    /**
     * 是否必填 1必填 2非必填
     */
    @ApiModelProperty("是否必填")
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否必填 1必填 2非必填'")
    private Integer required;

    /**
     * 字段长度
     */
    @ApiModelProperty("字段长度")
    @Column(columnDefinition = "int(11) DEFAULT NULL COMMENT '字段长度'")
    private Integer fieldLength;


    /**
     * mode id
     */
    @ApiModelProperty(value = "mode id")
    @Column(columnDefinition = "varchar(32) NOT NULL COMMENT 'mode id'")
    private String tableGuid;


    /**
     * 输入框类型
     */
    @ApiModelProperty(value = "输入框类型")
    @Column(columnDefinition = "varchar(32) DEFAULT NULL COMMENT '输入框类型'")
    private String inputType;

    /**
     * 输入提示
     */
    @ApiModelProperty(value = "输入提示")
    @Column(columnDefinition = "text COMMENT '输入提示'")
    private String placeholder;

    /**
     * 是否出现在表单 1是 2否
     */
    @ApiModelProperty(value = "是否出现在表单")
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否出现在表单 1是 2否'")
    private Integer isForm;

    /**
     * 1 允许为空 2 不允许为空
     */
    @ApiModelProperty(value = "是否允许为空")
    @Column(columnDefinition = "tinyint(1) NOT NULL DEFAULT '2' COMMENT '1 允许为空 2 不允许为空'")
    private Integer allowEmpty;

    /**
     * 选项列表 ## 隔开不同选项
     */
    @ApiModelProperty(value = "选项列表")
    @Column(name = "`option`", columnDefinition = "text COMMENT '选项列表 ## 隔开不同选项'")
    private String option;

    /**
     * 设置主键
     *
     * @param pk 主键
     */
    @Override
    public void setPrimaryKey(String pk) {
        setFieldGuid(pk);
    }

    /**
     * 获取主键
     *
     * @return
     */
    @Override
    public String getPrimaryKey() {
        return getFieldGuid();
    }
}
