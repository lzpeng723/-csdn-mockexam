package net.csdn.ac.c4.exam.repository;

import io.swagger.annotations.Api;
import net.csdn.ac.c4.exam.entity.Field;

import java.util.List;

/**
 * 字段 Repository
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:56
 */
@Api("字段")
public interface FieldRepository extends BaseRepository<Field> {

    /**
     * 通过 表单id 查询字段
     * @param tableGuid 表单id
     * @return
     */
    List<Field> findAllByTableGuidOrderByCreatedTime(String tableGuid);

    /**
     * 通过表 id 和 字段名 判断字段是否存在
     * @param tableGuid 表 id
     * @param field 字段名
     * @param fieldGuid 字段主键
     * @return
     */
    boolean existsByTableGuidAndFieldAndFieldGuidNot(String tableGuid, String field, String fieldGuid);

}