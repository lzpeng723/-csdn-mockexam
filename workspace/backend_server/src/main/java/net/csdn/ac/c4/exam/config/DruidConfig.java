//package net.csdn.ac.c4.exam.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
//import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
//import com.alibaba.druid.util.Utils;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * @author gblfy
// * @description Druid 底部广告去除
// * @date 2019/9/8 8:38
// * @version 1.0
// */
//@Profile("druid")
//@Configuration
//@ConditionalOnWebApplication
//@AutoConfigureAfter(DruidDataSourceAutoConfigure.class)
//@ConditionalOnProperty(name = "spring.datasource.druid.stat-view-servlet.enabled", havingValue = "true", matchIfMissing = true)
//public class DruidConfig {
//
//
//    /**
//     * 方法名: removeDruidAdFilterRegistrationBean
//     * 方法描述:  除去页面底部的广告
//     *
//     * @param properties
//     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
//     * @throws
//     */
//    @Bean
//    public FilterRegistrationBean<DruidFilter> removeDruidAdFilterRegistrationBean(DruidStatProperties properties) {
//        // 获取web监控页面的参数
//        DruidStatProperties.StatViewServlet config = properties.getStatViewServlet();
//        // 提取common.js的配置路径
//        String pattern = config.getUrlPattern() != null ? config.getUrlPattern() : "/druid/*";
//        String commonJsPattern = pattern.replaceAll("\\*", "js/common.js");
//        FilterRegistrationBean<DruidFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new DruidFilter());
//        registrationBean.addUrlPatterns(commonJsPattern);
//        return registrationBean;
//    }
//
//    class DruidFilter implements Filter{
//        /**
//         * @param filterConfig The configuration information associated with the
//         *                     filter instance being initialised
//         * @throws ServletException if the initialisation fails
//         */
//        @Override
//        public void init(FilterConfig filterConfig) throws ServletException {
//            Filter.super.init(filterConfig);
//        }
//
//        /**
//         * @param request  The request to process
//         * @param response The response associated with the request
//         * @param chain    Provides access to the next filter in the chain for this
//         *                 filter to pass the request and response to for further
//         *                 processing
//         * @throws IOException      if an I/O error occurs during this filter's
//         *                          processing of the request
//         * @throws ServletException if the processing fails for any other reason
//         */
//        @Override
//        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//            chain.doFilter(request, response);
//            // 重置缓冲区，响应头不会被重置
//            response.resetBuffer();
//            // 获取common.js
//            String filePath = "support/http/resources/js/common.js";
//            String text = Utils.readFromResource(filePath);
//            // 正则替换banner, 除去底部的广告信息
//            text = text.replaceAll("<a.*?banner\"></a><br/>", "");
//            text = text.replaceAll("powered.*?shrek.wang</a>", "");
//            response.getWriter().write(text);
//        }
//
//        /**
//         */
//        @Override
//        public void destroy() {
//            Filter.super.destroy();
//        }
//    }
//}
//
