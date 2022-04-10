package net.csdn.ac.c4.exam.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 列表查询条件
 *
 * @date: 2020/2/1
 * @time: 23:14
 * @author: lzpeng
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("分页查询结果")
public class QueryParam<T> {

    /**
     * 第几页
     */
    @ApiModelProperty("第几页")
    private int page = 1;

    /**
     * 每页数据条数
     */
    @ApiModelProperty("每页数据条数")
    private int size = 10;

    /**
     * 查询条件
     */
    @ApiModelProperty("查询条件")
    private T param;

}