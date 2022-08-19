package com.zhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 *
 * @author zhouhongyin
 * @since 2021/8/3 16:42
 */

@EnableAsync
@ServletComponentScan
@SpringBootApplication(scanBasePackages = {"org.jodconverter.boot.autoconfigure", "org.jodconverter", "com.zhy"})
public class JavaStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaStudyApplication.class, args);
    }
}
