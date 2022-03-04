package net.csdn.ac.tasking.springboot.controller;

import cn.hutool.core.util.TypeUtil;
import net.csdn.ac.tasking.springboot.entity.BaseEntity;
import net.csdn.ac.tasking.springboot.response.QueryResult;
import net.csdn.ac.tasking.springboot.response.Result;
import net.csdn.ac.tasking.springboot.response.ResultUtil;
import net.csdn.ac.tasking.springboot.service.BaseService;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 基础的Controller
 *
 * @date: 2020/2/5
 * @time: 22:12
 * @author: lzpeng
 * 谁说 HTTP GET 就不能通过 Body 来发送数据呢？ https://www.jianshu.com/p/c025273d78db
 */
public class BaseController<Entity extends BaseEntity> extends HttpBaseController{

    /**
     * 泛型注入
     */
    protected BaseService<Entity> baseService;


    protected BaseController(BaseService<Entity> baseService) {
        this.baseService = baseService;
    }


    /**
     * 保存
     *
     * @param entity
     * @return
     */

    public Result<Entity> save(Entity entity) {
        entity = baseService.save(entity);
        return ResultUtil.success(entity);
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */

    public Result<Void> delete(String id) {
        baseService.delete(id);
        return ResultUtil.success();
    }

    /**
     * 更新
     *
     * @param id
     * @param model
     * @return
     */

    public Result<Entity> update(String id, Entity model) {
        Entity entity = baseService.update(id, model);
        return ResultUtil.success(entity);
    }

    /**
     * 分页查询
     *
     * @param page
     * @param size
     * @param model
     * @return
     */
    public Result<QueryResult<Entity>> query(int page, int size, Entity model) {
        QueryResult<Entity> result = baseService.query(page, size, model);
        return ResultUtil.success(result);
    }


    /**
     * 根据id查询单条数据
     *
     * @param id
     * @return
     */

    public Result<Entity> findById(String id) {
        Entity entity = baseService.findById(id);
        return ResultUtil.success(entity);
    }

    /**
     * 查询所有数据
     *
     * @param model
     * @return
     */

    public Result<List<Entity>> findAll(Entity model) {
        List<Entity> entities = baseService.findAll(model);
        return ResultUtil.success(entities);
    }

    /**
     * 得到泛型参数
     *
     * @return
     */
    protected Class<Entity> getEntityClass() {
        Type type = TypeUtil.getTypeArgument(getClass());
        if (type != null && type instanceof Class) {
            return (Class<Entity>) type;
        }
        return null;
    }


}