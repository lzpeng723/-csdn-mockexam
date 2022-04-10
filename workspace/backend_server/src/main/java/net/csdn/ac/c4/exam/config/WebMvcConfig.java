package net.csdn.ac.c4.exam.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * WebMVC 配置
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 21:27
 */
@EnableWebMvc
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final BaseEntityMessageConverter baseEntityMessageConverter;

    public WebMvcConfig(BaseEntityMessageConverter baseEntityMessageConverter) {
        this.baseEntityMessageConverter = baseEntityMessageConverter;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

        /** 配置druid */
        registry.addResourceHandler("/druid/**")
                .addResourceLocations("classpath:/META-INF/resources/");

        /** 配置knife4j 显示文档 */
        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        /**
         * 配置swagger-ui显示文档
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        /** 公共部分内容 */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(baseEntityMessageConverter);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(localDateTimeConverter());
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new LocalDateTimeConverter();
    }

    /**
     * xss过滤拦截器
     */
    @Bean
    public FilterRegistrationBean<Filter> xssFilterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new XssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = new HashMap<>();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
        initParameters.put("isIncludeRichText", "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }


}
