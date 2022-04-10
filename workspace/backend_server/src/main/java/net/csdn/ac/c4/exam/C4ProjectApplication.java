package net.csdn.ac.c4.exam;

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
public class C4ProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(C4ProjectApplication.class, args);
    }

}
