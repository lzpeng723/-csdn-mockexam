package net.csdn.ac.c4.exam.config;

import cn.hutool.core.lang.Snowflake;
import net.csdn.ac.c4.exam.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.persistence.PrePersist;

/**
 * id 生成器
 * 生成规则 实体类全路径截取跟包之后 + snowflake 生成的id 并转为16进制大写
 *
 * @date: 2020/2/1
 * @time: 22:28
 * @author: lzpeng
 */
@Component
public class GenerateEntityIdListener {

    @Autowired
    private Snowflake snowflake;

    @PrePersist
    public void touchForCreate(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        if (target instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) target;
            String strId = snowflake.nextIdStr();
            entity.setPrimaryKey(strId);
        }
    }


     //@PostLoad
    public void touchForLoad(Object target) {
        Assert.notNull(target, "Entity must not be null!");
        if (target instanceof BaseEntity) {
            BaseEntity entity = (BaseEntity) target;
            //entity.setCreate_time(entity.getCreateTime());
        }
    }

}
