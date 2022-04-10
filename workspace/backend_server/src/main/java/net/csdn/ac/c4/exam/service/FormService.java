package net.csdn.ac.c4.exam.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.Page;
import cn.hutool.db.PageResult;
import lombok.extern.slf4j.Slf4j;
import net.csdn.ac.c4.exam.entity.Field;
import net.csdn.ac.c4.exam.entity.FormData;
import net.csdn.ac.c4.exam.entity.Table;
import net.csdn.ac.c4.exam.repository.FieldRepository;
import net.csdn.ac.c4.exam.response.QueryResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lzpeng
 * @version 1.0
 * @date 2022/4/4 17:33
 */
@Slf4j
@Service
@Transactional(rollbackOn = Throwable.class)
public class FormService {


    private final TableService tableService;

    private final FieldRepository fieldRepository;


    private final Db db;


    private final Snowflake snowflake;


    public FormService(TableService tableService, FieldRepository fieldRepository, Db db, Snowflake snowflake) {
        this.tableService = tableService;
        this.fieldRepository = fieldRepository;
        this.db = db;
        this.snowflake = snowflake;
    }

    /**
     * 推送表单
     *
     * @param formData 表单数据
     * @return
     */
    public FormData push(FormData formData) throws SQLException {
        Date date = new Date();
        Table table = tableService.findById(formData.getTableGuid());
        String tableName = table.getTableName();
        Entity entity = Entity.create(tableName);
        entity.set("_id", snowflake.nextIdStr());
        entity.set("created_time", date);
        entity.set("updated_time", date);
        entity.set("created_by", "");
        entity.set("updated_by", "");
        Map<String, Object> form = formData.getForm();
        form.forEach(entity::set);
        db.insert(entity);
        return formData;
    }

    /**
     * 查询表单元数据
     *
     * @param tableGuid 表单id
     * @return
     */
    public List<Field> header(String tableGuid) {
        return fieldRepository.findAllByTableGuidOrderByCreatedTime(tableGuid);
    }

    /**
     * 查询表单元数据
     *
     * @param page      第几页
     * @param size      每页数据
     * @param tableGuid 表单id
     * @return
     */
    public QueryResult<Entity> table(int page, int size, String tableGuid) throws SQLException {
        Table table = tableService.findById(tableGuid);
        String tableName = table.getTableName();
        // 处理不正确的页码
        page = optimizePage(page);
        // 处理不正确的每页数据量
        size = optimizeSize(size);
        Page pageCond = Page.of(page, size);
        PageResult<Entity> pageResult = db.page("SELECT * FROM `" + tableName + "`", pageCond);
        // 执行查询后操作
        return new QueryResult<>(pageResult, pageResult.getTotal(), pageResult.getPage() + 1, pageResult.getTotalPage());

    }

    /**
     * 优化每页数据
     *
     * @param size
     * @return
     */
    public int optimizeSize(int size) {
        if (size <= 0) {
            // 如果传入size 不合法则设置为 20
            size = 20;
        }
        return size;
    }

    /**
     * 优化页码
     *
     * @param page
     * @return
     */
    public int optimizePage(int page) {
        if (page <= 0) {
            page = 1;
        }
        //为了适应数据库,将页码减1
        page = page - 1;
        return page;
    }
}
