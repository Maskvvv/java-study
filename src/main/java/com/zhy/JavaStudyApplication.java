package com.zhy;

import com.zhy.spring.scan.EnableMyRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author zhouhongyin
 * @since 2021/8/3 16:42
 */
@EnableTransactionManagement
@EnableMyRegistry(basePackages = "com.zhy.spring.scan.model")
@EnableAsync
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"org.jodconverter.boot.autoconfigure", "org.jodconverter", "com.zhy"})
public class JavaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaStudyApplication.class, args);
    }
}
