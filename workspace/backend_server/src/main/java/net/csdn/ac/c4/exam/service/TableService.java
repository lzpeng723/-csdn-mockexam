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
import net.csdn.ac.c4.exam.repository.TableRepository;
import net.csdn.ac.c4.exam.util.BizException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表单 Service
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 21:52
 */
@Service
@Transactional(rollbackOn = Throwable.class)
public class TableService extends BaseService<Table> {
    /**
     * 缓存名称
     */
    protected static final String ENTITY_NAME = "net.csdn.ac.c4.exam.entity.Table";

    /**
     * 表单 Repository
     */
    private final TableRepository tableRepository;

    private final FieldRepository fieldRepository;


    private final Db db;


    private final DataSource dataSource;

    private final Snowflake snowflake;

    public TableService(TableRepository tableRepository, FieldRepository fieldRepository, Db db, DataSource dataSource, Snowflake snowflake) {
        super(tableRepository);
        this.tableRepository = tableRepository;
        this.fieldRepository = fieldRepository;
        this.db = db;
        this.dataSource = dataSource;
        this.snowflake = snowflake;
    }


    @Override
    protected boolean beforeSave(Table table) {
        if (tableRepository.existsByTableNameAndTableGuidNot(table.getTableName(), String.valueOf(table.getTableGuid()))) {
            throw new BizException("数据表 " + table.getTableName() + " 已存在");
        }
        if (table.getPrimaryKey() == null) {
            table.setStatus(1);
        }
        return super.beforeSave(table);
    }

    @Override
    @CachePut(value = ENTITY_NAME, key = "#result.primaryKey", unless = "#result == null")
    public Table save(Table table) {
        return super.save(table);
    }

    @Override
    @CacheEvict(value = ENTITY_NAME, key = "#id")
    public void delete(String id) {
        super.delete(id);
    }

    @Override
    @CachePut(value = ENTITY_NAME, key = "#id", unless = "#result == null")
    public Table update(String id, Table model) {
        return super.update(id, model);
    }

    @Override
    @Cacheable(value = ENTITY_NAME, key = "#id", unless = "#result == null")
    public Table findById(String id) {
        return super.findById(id);
    }


    /**
     * 从 json 读取数据
     *
     * @param json
     * @return
     * @throws JsonProcessingException
     */
    @Override
    public List<Table> readDataFromJson(String json) throws JsonProcessingException {
        List<Table> list = objectMapper.readValue(json, new TypeReference<List<Table>>() {
        });
        return list;
    }


    @Override
    protected void afterSave(Table table) {
        super.afterSave(table);
        this.createTable(table);
        if (fieldRepository.existsByTableGuidAndFieldAndFieldGuidNot( table.getTableGuid(), "_id", table.getTableGuid())) {
            return;
        }
        Field field = new Field();
        field.setFieldGuid(snowflake.nextIdStr());
        field.setTableGuid(table.getTableGuid());
        field.setField("_id");
        field.setFieldName("主键");
        field.setType("VARCHAR");
        field.setIsForm(2);
        field.setRequired(2);
        fieldRepository.save(field);
    }

    public void createTable(String tableGuid) {
        Table table = findById(tableGuid);
        createTable(table);
    }

    @SneakyThrows
    public void createTable(Table table) {
        InputStream createTableSqlIs = getClass().getClassLoader().getResourceAsStream("create-table.sql");
        String createTableSqlStr = StreamUtils.copyToString(createTableSqlIs, Charset.defaultCharset());
        String createTableSql = createTableSqlStr
                .replace("${tableName}", table.getTableName())
                .replace("${tableAlias}", table.getTableAlias());
        db.execute(createTableSql);
    }


    @Override
    @SneakyThrows
    protected boolean beforeDelete(Table table) {
        String tableName = table.getTableName();
        BigDecimal count = (BigDecimal) db.queryNumber("SELECT count(*) FROM `" + tableName + "`");
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            throw new BizException("该表单已被用户填写，不允许删除");
        }
        String deleteColumnSqlBuilder = "DROP TABLE `" +
                tableName +
                "`;";
        db.execute(deleteColumnSqlBuilder);
        return super.beforeDelete(table);
    }

    @Override
    protected void afterDelete(Table table) {
        super.afterDelete(table);
        List<Field> fieldList = fieldRepository.findAllByTableGuidOrderByCreatedTime(table.getTableGuid());
        if (fieldList.isEmpty()) {
            return;
        }
        List<String> fieldIdList = fieldList.stream().map(Field::getFieldGuid).collect(Collectors.toList());
        fieldRepository.deleteAllById(fieldIdList);
    }
}
