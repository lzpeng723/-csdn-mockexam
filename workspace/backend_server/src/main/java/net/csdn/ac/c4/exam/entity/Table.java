package net.csdn.ac.c4.exam.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 表单
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/16 0:27
 */
@Data
@Entity
@ApiModel("表单")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Table extends BaseEntity {


    @Id
    @ApiModelProperty(value = "主键ID", hidden = true)
    @Column(columnDefinition = "varchar(255) COMMENT 'id 主键'", updatable = false)
    private String tableGuid;

    /**
     * 表别名
     */
    @ApiModelProperty("表别名")
    @Column(columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '表别名'", nullable = false)
    private String tableAlias;

    /**
     * 表名
     */
    @ApiModelProperty("表名")
    @Column(columnDefinition = "varchar(255) NOT NULL DEFAULT '' COMMENT '表名'", nullable = false, unique = true)
    private String tableName;

    /**
     * 状态。1启用 2禁用
     */
    @ApiModelProperty("状态")
    @Column(name = "`status`", columnDefinition = "tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1启用 2禁用'", nullable = false)
    private Integer status;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    @Column(name = "`describe`", columnDefinition = "text COMMENT '描述'")
    private String describe;

    /**
     * 设置主键
     *
     * @param pk 主键
     */
    @Override
    public void setPrimaryKey(String pk) {
        setTableGuid(pk);
    }

    /**
     * 获取主键
     *
     * @return
     */
    @Override
    public String getPrimaryKey() {
        return getTableGuid();
    }
}
