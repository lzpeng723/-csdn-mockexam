package net.csdn.ac.tasking.springboot.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.tasking.springboot.entity.User;
import net.csdn.ac.tasking.springboot.response.QueryResult;
import net.csdn.ac.tasking.springboot.response.Result;
import net.csdn.ac.tasking.springboot.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户 控制层
 *
 * @author : lzpeng
 * @date : 2020-5-16
 * @time : 21:03:56
 */
@Slf4j
@Validated
@RestController
@CrossOrigin
@RequestMapping("/user")
@Api(tags = "用户管理接口", value = "用户管理，提供用户的增、删、改、查")
public class UserController extends BaseController<User> {

    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "sys";
    /**
     * 类名称
     */
    private static final String CLASS_NAME = "User";
    /**
     * 用户列表权限
     */
    private static final String LIST_PERM = MODULE_NAME + ":" + CLASS_NAME + ":list";
    /**
     * 用户查询权限
     */
    private static final String QUERY_PERM = MODULE_NAME + ":" + CLASS_NAME + ":query";
    /**
     * 用户新增权限
     */
    private static final String ADD_PERM = MODULE_NAME + ":" + CLASS_NAME + ":add";
    /**
     * 用户删除权限
     */
    private static final String DELETE_PERM = MODULE_NAME + ":" + CLASS_NAME + ":delete";
    /**
     * 用户修改权限
     */
    private static final String EDIT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":edit";
    /**
     * 用户导出权限
     */
    private static final String EXPORT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":export";
    /**
     * 用户导入权限
     */
    private static final String IMPORT_PERM = MODULE_NAME + ":" + CLASS_NAME + ":import";

    /**
     * 用户Service
     */
    private UserService userService;

    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
    }

    @Override
    @PostMapping
    @ApiOperation("保存用户")
    //@PreAuthorize("hasAnyAuthority('" + ADD_PERM + "')")
    public Result<User> save(@Valid @RequestBody User model) {
        return super.save(model);
    }

    @Override
    @DeleteMapping("/{id:^\\d+$}")
    @ApiOperation("删除用户")
    //@PreAuthorize("hasAnyAuthority('" + DELETE_PERM + "')")
    public Result<Void> delete(@ApiParam(value = "主键id", required = true) @PathVariable("id") String id) {
        return super.delete(id);
    }

    @Override
    @PutMapping("/{id:^\\d+$}")
    @ApiOperation("更新用户")
    //@PreAuthorize("hasAnyAuthority('" + EDIT_PERM + "')")
    public Result<User> update(@ApiParam(value = "主键id", required = true) @PathVariable("id") String id, @RequestBody User model) {
        return super.update(id, model);
    }

    @Override
    @GetMapping
    @ApiOperation("查询所有用户")
    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
    public Result<List<User>> findAll(User model) {
        return super.findAll(model);
    }

    @Override
    @GetMapping("/{page:^\\d+$}/{size:^\\d+$}")
    @ApiOperation("分页查询用户列表")
    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
    public Result<QueryResult<User>> query(@ApiParam(value = "页码", required = true) @PathVariable("page") int page, @ApiParam(value = "每页数据条数", required = true) @PathVariable("size") int size, User model) {
        return super.query(page, size, model);
    }

    @Override
    @GetMapping("/{id:^\\d+$}")
    @ApiOperation("根据ID查询用户")
    //@PreAuthorize("hasAnyAuthority('" + QUERY_PERM + "')")
    public Result<User> findById(@ApiParam(value = "主键id", required = true) @PathVariable("id") String id) {
        return super.findById(id);
    }

}