package net.csdn.ac.c4.exam.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import net.csdn.ac.c4.exam.C4ProjectApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Knife4j 配置
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/2/22 19:21
 */
@Configuration
@EnableKnife4j
@EnableSwagger2
@ConditionalOnMissingClass("org.junit.Test")
public class SwaggerConfiguration {

    @Bean
    public Docket defaultApi(@Value("${server.port:8080}") int port) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("CSDN C4 项目考试")
                        .description("CSDN C4 项目考试 动态表单")
                        .termsOfServiceUrl("http://localhost:" + port)
                        .contact(new Contact("lzpeng723", "http://localhost" + port, "lzpeng723@gmail.com"))
                        .version("1.0")
                        .build())
                //分组名称
                .groupName("动态表单")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(C4ProjectApplication.class.getPackage().getName()))
                .paths(PathSelectors.any())
                .build();
    }
}
