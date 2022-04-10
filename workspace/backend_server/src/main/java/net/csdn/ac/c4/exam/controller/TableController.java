package net.csdn.ac.c4.exam.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.c4.exam.entity.Table;
import net.csdn.ac.c4.exam.response.QueryResult;
import net.csdn.ac.c4.exam.response.Result;
import net.csdn.ac.c4.exam.response.ResultUtil;
import net.csdn.ac.c4.exam.service.TableService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

/**
 * 表单 控制层
 *
 * @author : lzpeng
 * @date : 2020-5-16
 * @time : 21:03:56
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@Api(tags = "表单管理接口", value = "表单管理，提供表单的增、删、改、查")
public class TableController extends BaseController<Table> {

    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "sys";
    /**
     * 类名称
     */
    private static final String CLASS_NAME = "Table";
    /**
     * 表单列表权限
     */
    private static final String LIST_PERM = MODULE_NAME + ":" + CLASS_NAME + ":list";
    /**
     * 表单查询权限
     */
    private static final String QUERY_PERM = MODULE_NAME + ":" + CLASS_NAME + ":query";
    /**
     * 表单新增权限
     */
    private static final String ADD_PERM = MODULE_NAME + ":" + CLASS_NAME + ":add";
    /**
     * 表单删除权限
     */
    private static final String DELETE_PERM = MODULE_NAME + ":" + CLASS_NAME + ":delete";
    /**
     * 表单修改权限
     */
    private static final String EDIT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":edit";
    /**
     * 表单导出权限
     */
    private static final String EXPORT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":export";
    /**
     * 表单导入权限
     */
    private static final String IMPORT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":import";

    /**
     * 表单Service
     */
    private TableService tableService;


    public TableController(TableService tableService) {
        super(tableService);
        this.tableService = tableService;
    }


    @PostMapping("/table/list")
    @ApiOperation("分页查询表单列表")
    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
    //public Result<QueryResult<Table>> queryTable(@ApiParam(value = "页码", required = true)  int page, @ApiParam(value = "每页数据条数", required = true) int size, Table model) {
    public Result<QueryResult<Table>> queryTable(@RequestBody Map<String, Object> bodyMap) throws IOException {
        int page = (int) bodyMap.remove("page");
        int size = (int) bodyMap.remove("size");
        Table table = mapToObj(bodyMap, Table.class);
        return super.query(page, size, table);
    }


    @Override
    @PostMapping("/table/create")
    @ApiOperation("保存表单")
    //@PreAuthorize("hasAnyAuthority('" + ADD_PERM + "')")
    public Result save(@Valid @RequestBody Table model) {
        super.save(model);
        return ResultUtil.success("创建成功");
    }

    @PostMapping("/table/del")
    @ApiOperation("删除表单")
    //@PreAuthorize("hasAnyAuthority('" + DELETE_PERM + "')")
    public Result delete(@Valid @RequestBody Table model) {
        super.delete(model.getTableGuid());
        return ResultUtil.success("删除成功");
    }

    @PostMapping({"/table/update", "/table/status"})
    @ApiOperation("更新表单")
    //@PreAuthorize("hasAnyAuthority('" + EDIT_PERM + "')")
    public Result update(@Valid @RequestBody Table model) {
        super.update(model.getTableGuid(), model);
        return ResultUtil.success("修改成功");
    }

//    @Override
//    //@GetMapping
//    @ApiOperation("查询所有表单")
//    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
//    public Result<List<Table>> findAll(Table model) {
//        return super.findAll(model);
//    }
//
//    @Override
//    @GetMapping("/{page:^\\d+$}/{size:^\\d+$}")
//    @ApiOperation("分页查询表单列表")
//    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
//    public Result<QueryResult<Table>> query(@ApiParam(value = "页码", required = true) @PathVariable("page") int page, @ApiParam(value = "每页数据条数", required = true) @PathVariable("size") int size, Table model) {
//        return super.query(page, size, model);
//    }
//
//    @Override
//    @GetMapping("/{id:^\\d+$}")
//    @ApiOperation("根据ID查询表单")
//    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
//    public Result<Table> findById(@ApiParam(value = "主键id", required = true) @PathVariable("id") String id) {
//        return super.findById(id);
//    }

}