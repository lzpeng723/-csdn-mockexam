package net.csdn.ac.c4.exam.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @date: 2020/2/1
 * @time: 23:03
 * @author: lzpeng
 */
@Data
@NoArgsConstructor
@ApiModel("Rest接口返回结果")
public class Result<T> {
    /**
     * 操作代码
     */
    @ApiModelProperty("操作代码")
    private int code;

    /**
     * 提示信息
     */
    @ApiModelProperty("提示信息")
    private String msg;

    /**
     * 详细数据
     */
    @ApiModelProperty("详细数据")
    private T data;

    private Result(ResultCode result) {
        this(result, null);
    }

    private Result(ResultCode result, T data) {
        this.msg = result.message();
        this.code = result.code();
        this.data = data;
    }

    static Result create(ResultCode result) {
        return new Result(result);
    }

    static <T> Result<T> create(ResultCode result, T data) {
        return new Result(result, data);
    }
}