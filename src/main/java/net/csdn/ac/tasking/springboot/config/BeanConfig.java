package net.csdn.ac.tasking.springboot.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
     * 终端 ID
     */
    private long workerId = 0;

    /**
     * 数据中心 ID
     */
    private long datacenterId = 0;

    @Bean
    public Snowflake snowflake() {
        return IdUtil.getSnowflake(workerId, datacenterId);
    }


}
