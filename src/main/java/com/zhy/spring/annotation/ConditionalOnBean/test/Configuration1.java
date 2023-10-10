package com.zhy.spring.annotation.ConditionalOnBean.test;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> @ConditionalOnBean 不生效 </p>
 *
 * <p>https://blog.csdn.net/forezp/article/details/84313907</p>
 * @author zhouhongyin
 * @since 2023/10/10 11:12
 */
@Configuration
public class Configuration1 {

    @Bean
    @ConditionalOnBean(Bean1.class)
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }
}
