package net.csdn.ac.c4.exam.service;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.db.Db;
import cn.hutool.db.meta.MetaUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.SneakyThrows;
import net.csdn.ac.c4.exam.entity.Field;
import net.csdn.ac.c4.exam.entity.Table;
import net.csdn.ac.c4.exam.repository.FieldRepository;
import net.csdn.ac.c4.exam.response.QueryResult;
import net.csdn.ac.c4.exam.util.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字段 Service
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 21:52
 */
@Service
@Transactional(rollbackOn = Throwable.class)
public class FieldService extends BaseService<Field> {


    /**
     * 缓存名称
     */
    protected static final String ENTITY_NAME = "net.csdn.ac.c4.exam.entity.Field";

    /**
     * 内置字段
     */
    private final List<String> innerFieldList;

    /**
     * 字段 Repository
     */
    private final FieldRepository fieldRepository;


    private final TableService tableService;

    private final Db db;

    private final DataSource dataSource;

    private final Snowflake snowflake;


    public FieldService(FieldRepository fieldRepository, TableService tableService, Db db, DataSource dataSource,Snowflake snowflake, @Value("${csdn.inner-field-name}") List<String> innerFieldList) {
        super(fieldRepository);
        this.fieldRepository = fieldRepository;
        this.tableService = tableService;
        this.db = db;
        this.dataSource = dataSource;
        this.snowflake = snowflake;
        this.innerFieldList = innerFieldList;
    }


    @Override
    protected boolean beforeSave(Field field) {
        if (innerFieldList.contains(field.getField())) {
            throw new BizException("字段 " + field.getField() + " 已存在");
        }
        if (fieldRepository.existsByTableGuidAndFieldAndFieldGuidNot(field.getTableGuid(), field.getField(), String.valueOf(field.getFieldGuid()))) {
            throw new BizException("字段 " + field.getField() + " 已存在");
        }
        return super.beforeSave(field);
    }

    @Override
    @CachePut(value = ENTITY_NAME, key = "#result.primaryKey", unless = "#result == null")
    public Field save(Field field) {
        return super.save(field);
    }

    @Override
    @CacheEvict(value = ENTITY_NAME, key = "#id")
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    @CachePut(value = ENTITY_NAME, key = "#id", unless = "#result == null")
    public Field update(String id, Field model) {
        return super.update(id, model);
    }

    @Override
    @Cacheable(value = ENTITY_NAME, key = "#id", unless = "#result == null")
    public Field findById(String id) {
        return super.findById(id);
    }


    @Override
    public List<Field> findAll(Field model, Sort sort) {
        String tableGuid = model.getTableGuid();
        Table table = tableService.findById(tableGuid);
        if (table == null) {
            throw new BizException("表单不存在");
        }
        Integer status = table.getStatus();
        if (2 == status) {
            throw new BizException("表单已禁用");
        }
        return super.findAll(model, sort);
    }

    /**
     * 从 json 读取数据
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public List<Field> readDataFromJson(String json) throws JsonProcessingException {
        List<Field> list = objectMapper.readValue(json, new TypeReference<List<Field>>() {
        });
        return list;
    }


    @Override
    @SneakyThrows
    protected void afterSave(Field field) {
        super.afterSave(field);
        String columnName = field.getField();
        Table table = tableService.findById(field.getTableGuid());
        String tableName = table.getTableName();
        List<String> columnNameList = Arrays.asList(MetaUtil.getColumnNames(dataSource, tableName));
        String op = columnNameList.contains(columnName) ? "MODIFY" : "ADD";
        Integer fieldLength = field.getFieldLength();
        Integer allowEmpty = field.getAllowEmpty();
        StringBuilder addColumnSqlBuilder = new StringBuilder()
                .append("ALTER TABLE `")
                .append(tableName)
                .append("` ")
                .append(op)
                .append(" COLUMN `")
                .append(columnName)
                .append("` ")
                .append(field.getType())
                .append(" ");
        if (fieldLength != null) {
            addColumnSqlBuilder
                    .append("(")
                    .append(fieldLength)
                    .append(") ");
        }
        if (allowEmpty != null && allowEmpty == 2) {
            addColumnSqlBuilder.append(" NOT NULL ");
        }
        addColumnSqlBuilder
                .append(" COMMENT '")
                .append(field.getFieldName())
                .append("';");
        db.execute(addColumnSqlBuilder.toString());
    }

    @Override
    @SneakyThrows
    protected boolean beforeDelete(Field field) {
        Table table = tableService.findById(field.getTableGuid());
        String tableName = table.getTableName();
        String deleteColumnSqlBuilder = "ALTER TABLE `" +
                tableName +
                "` DROP COLUMN `" +
                field.getField() +
                "`;";
        db.execute(deleteColumnSqlBuilder);
        return super.beforeDelete(field);
    }
}
