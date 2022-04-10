package net.csdn.ac.c4.exam.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @author lzpeng
 * @version 1.0
 * @date 2022/4/4 17:31
 */
@Data
public class FormData {


    /**
     * mode id
     */
    @ApiModelProperty(value = "mode id")
    private String tableGuid;

    /**
     * 表单数据
     */
    @ApiModelProperty(value = "表单数据")
    private Map<String, Object> form;

}
