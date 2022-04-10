package net.csdn.ac.c4.exam.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.db.Db;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Bean 配置
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 15:41
 */
@Configuration
public class BeanConfig {


    /**
     *
     * @param workerId 终端 ID
     * @param dataCenterId 数据中心 ID
     * @return
     */
    @Bean
    public Snowflake snowflake(@Value("${csdn.worker-id:0}") long workerId, @Value("${csdn.data-center-id:0}") long dataCenterId) {
        return IdUtil.getSnowflake(workerId, dataCenterId);
    }


    @Bean
    public Db db(DataSource dataSource) {
        return Db.use(dataSource);
    }


}
