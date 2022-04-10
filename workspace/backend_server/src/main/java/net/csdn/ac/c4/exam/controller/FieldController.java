package net.csdn.ac.c4.exam.controller;


import cn.hutool.core.lang.Snowflake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.c4.exam.entity.Field;
import net.csdn.ac.c4.exam.response.QueryResult;
import net.csdn.ac.c4.exam.response.Result;
import net.csdn.ac.c4.exam.response.ResultUtil;
import net.csdn.ac.c4.exam.service.FieldService;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 字段 控制层
 *
 * @author : lzpeng
 * @date : 2020-5-16
 * @time : 21:03:56
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@Api(tags = "字段管理接口", value = "字段管理，提供字段的增、删、改、查")
public class FieldController extends BaseController<Field> {

    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "sys";
    /**
     * 类名称
     */
    private static final String CLASS_NAME = "Field";
    /**
     * 字段列表权限
     */
    private static final String LIST_PERM = MODULE_NAME + ":" + CLASS_NAME + ":list";
    /**
     * 字段查询权限
     */
    private static final String QUERY_PERM = MODULE_NAME + ":" + CLASS_NAME + ":query";
    /**
     * 字段新增权限
     */
    private static final String ADD_PERM = MODULE_NAME + ":" + CLASS_NAME + ":add";
    /**
     * 字段删除权限
     */
    private static final String DELETE_PERM = MODULE_NAME + ":" + CLASS_NAME + ":delete";
    /**
     * 字段修改权限
     */
    private static final String EDIT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":edit";
    /**
     * 字段导出权限
     */
    private static final String EXPORT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":export";
    /**
     * 字段导入权限
     */
    private static final String IMPORT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":import";

    /**
     * 字段Service
     */
    private FieldService fieldService;

    private final Snowflake snowflake;


    public FieldController(FieldService fieldService, Snowflake snowflake) {
        super(fieldService);
        this.fieldService = fieldService;
        this.snowflake = snowflake;
    }


    @PostMapping("/field/list")
    @ApiOperation("分页查询字段列表")
    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
    //public Result<QueryResult<Field>> queryField(@ApiParam(value = "页码", required = true)  int page, @ApiParam(value = "每页数据条数", required = true) int size, Field model) {
    public Result<QueryResult<Field>> queryField(@RequestBody Map<String, Object> bodyMap) throws IOException {
        int page = (int) bodyMap.remove("page");
        int size = (int) bodyMap.remove("size");
        Field field = mapToObj(bodyMap, Field.class);
        QueryResult<Field> queryResult = fieldService.query(page, size, Sort.by(Sort.Direction.ASC, "createdTime"), field);
        return ResultUtil.success(queryResult);
    }


    @Override
    @PostMapping("/field/add")
    @ApiOperation("保存字段")
    //@PreAuthorize("hasAnyAuthority('" + ADD_PERM + "')")
    public Result save(@Valid @RequestBody Field model) {
        super.save(model);
        return ResultUtil.success("添加成功");
    }

    @PostMapping("/field/del")
    @ApiOperation("删除字段")
    //@PreAuthorize("hasAnyAuthority('" + DELETE_PERM + "')")
    public Result delete(@Valid @RequestBody Field model) {
        super.delete(model.getFieldGuid());
        return ResultUtil.success("删除成功");
    }

    @PostMapping({"/field/update", "/field/status"})
    @ApiOperation("更新字段")
    //@PreAuthorize("hasAnyAuthority('" + EDIT_PERM + "')")
    public Result update(@Valid @RequestBody Field model) {
        super.update(model.getFieldGuid(), model);
        return ResultUtil.success("修改成功");
    }
//
//    @Override
//    @GetMapping("/{page:^\\d+$}/{size:^\\d+$}")
//    @ApiOperation("分页查询字段列表")
//    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
//    public Result<QueryResult<Field>> query(@ApiParam(value = "页码", required = true) @PathVariable("page") int page, @ApiParam(value = "每页数据条数", required = true) @PathVariable("size") int size, Field model) {
//        return super.query(page, size, model);
//    }
//
//    @Override
//    @GetMapping("/{id:^\\d+$}")
//    @ApiOperation("根据ID查询字段")
//    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
//    public Result<Field> findById(@ApiParam(value = "主键id", required = true) @PathVariable("id") String id) {
//        return super.findById(id);
//    }

}