package net.csdn.ac.check.core.configure;

import net.csdn.ac.check.core.constant.Constant;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.util.concurrent.TimeUnit;

/**
 * Web服务设置
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@Configuration
@Component
public class WebConfiguration extends WebMvcConfigurationSupport {
    /**
     * 资源路径管理
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/")
                .addResourceLocations("classpath:static/")
                .addResourceLocations("classpath:templates/");
        super.addResourceHandlers(registry);
    }

    /**
     * 跨域支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 允许跨域的域名，可以用*表示允许任何域名使用
                .allowedOrigins("*")
                // 允许任何请求头
                .allowedHeaders("*")
                // 带上cookie信息
                .allowCredentials(true)
                .allowedMethods("*")
                // maxAge(3600 * 24)表明在3600 * 24秒内，不需要再发送预检验请求，可以缓存该结果
                .maxAge(Constant.EXPIRE_ONE_DAY_SECOND_TIMEOUT);
    }

    /**
     * 进程外缓存初始化
     *
     * @return
     */
    @Bean("cache")
    public LoadingCache<String, String> cache() {
        return Caffeine.newBuilder()
                .initialCapacity(1024)
                .maximumSize(1024)
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build(key -> {
                    return "";
                });
    }
}
