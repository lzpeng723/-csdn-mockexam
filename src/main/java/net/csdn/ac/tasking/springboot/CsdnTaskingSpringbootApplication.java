package net.csdn.ac.tasking.springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;

/**
 *
 */
@Slf4j
@EnableCaching
@EnableJpaAuditing
@ServletComponentScan
@SpringBootApplication
@Import(SpringDataRestConfiguration.class)
public class CsdnTaskingSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsdnTaskingSpringbootApplication.class, args);
    }

}
