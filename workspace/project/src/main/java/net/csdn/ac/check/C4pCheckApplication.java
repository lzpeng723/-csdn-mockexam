package net.csdn.ac.check;

import net.csdn.ac.check.core.base.BaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 应用启动类
 *
 * @author xiangwang
 * @since 2021-06-18
 * @version 1.0
 */
@EnableCaching
@SpringBootApplication
@EnableTransactionManagement
public class C4pCheckApplication extends BaseObject {
    protected static Logger logger = LoggerFactory.getLogger(C4pCheckApplication.class);

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        logger.warn("starting check ......");
        SpringApplication application = new SpringApplication(C4pCheckApplication.class);
        application.setBannerMode(Banner.Mode.OFF);
        application.run(args);
        long end = System.currentTimeMillis();
        logger.warn("started check in {} second", (end - start) / 1000);
    }
}
