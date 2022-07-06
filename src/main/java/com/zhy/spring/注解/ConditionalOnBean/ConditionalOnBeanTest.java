package com.zhy.spring.注解.ConditionalOnBean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhouhongyin
 * @since 2022/7/4 13:58
 */
@Configuration
public class ConditionalOnBeanTest {

    @Bean
    @ConditionalOnBean(name = "people2")
    //@ConditionalOnMissingBean(name = "people2")
    public People people1() {
        return new People("people1", "1");
    }


    //@Bean
    public People people2() {
        return new People("people2", "2");
    }

}
