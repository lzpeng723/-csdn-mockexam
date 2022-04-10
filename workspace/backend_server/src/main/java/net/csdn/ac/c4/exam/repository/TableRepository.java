package net.csdn.ac.c4.exam.repository;

import io.swagger.annotations.Api;
import net.csdn.ac.c4.exam.entity.Table;

/**
 * 表单 Repository
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:56
 */
@Api("表单")
public interface TableRepository extends BaseRepository<Table> {

    /**
     * 通过表名判断表是否存在
     * @param tableName 表名
     * @param tableGuid 表 id
     * @return
     */
    boolean existsByTableNameAndTableGuidNot(String tableName, String tableGuid);

}