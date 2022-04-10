package net.csdn.ac.c4.exam.controller;

import cn.hutool.db.Entity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.c4.exam.entity.Field;
import net.csdn.ac.c4.exam.entity.FormData;
import net.csdn.ac.c4.exam.response.QueryResult;
import net.csdn.ac.c4.exam.response.Result;
import net.csdn.ac.c4.exam.response.ResultUtil;
import net.csdn.ac.c4.exam.service.FieldService;
import net.csdn.ac.c4.exam.service.FormService;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 表单控制器
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/4/4 17:29
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@Api(tags = "动态表单管理接口", value = "动态表单管理，提供动态表单的增、删、改、查")
public class FormController extends BaseHttpController{


    private FormService formService;

    private FieldService fieldService;


    public FormController(FormService formService, FieldService fieldService) {
        this.formService = formService;
        this.fieldService = fieldService;
    }

    @PostMapping("/form/get")
    @ApiOperation("查询所有字段")
    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
    public Result<Map<String, Object>> get(@RequestBody Field model) {
        model.setIsForm(1);
        List<Field> fieldList = fieldService.findAll(model, Sort.by(Sort.Direction.ASC, "createdTime"));
        return ResultUtil.success(Collections.singletonMap("fields", fieldList));
    }

    @PostMapping("/form/push")
    @ApiOperation("新增动态表单数据")
    //@PreAuthorize("hasAnyAuthority('" + ADD_PERM + "')")
    public Result<String> push(@Valid @RequestBody FormData formData) throws SQLException {
        formService.push(formData);
        return ResultUtil.success("提交成功");
    }

    @PostMapping("/form/header")
    @ApiOperation("查询动态表单元数据")
    //@PreAuthorize("hasAnyAuthority('" + ADD_PERM + "')")
    public Result<List<Field>> header(@RequestBody Map<String, Object> bodyMap) {
        String tableGuid = (String) bodyMap.remove("table_guid");
        return ResultUtil.success(formService.header(tableGuid));
    }

    @PostMapping("/form/table")
    @ApiOperation("查询动态表单数据")
    //@PreAuthorize("hasAnyAuthority('" + ADD_PERM + "')")
    public Result<QueryResult<Entity>> table(@RequestBody Map<String, Object> bodyMap) throws SQLException {
        int page = (int) bodyMap.remove("page");
        int size = (int) bodyMap.remove("size");
        String tableGuid = (String) bodyMap.remove("table_guid");
        return ResultUtil.success(formService.table(page,size,tableGuid));
    }

}
