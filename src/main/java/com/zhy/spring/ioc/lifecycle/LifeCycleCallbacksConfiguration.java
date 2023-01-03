package com.zhy.spring.ioc.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author zhouhongyin
 * @since 2023/1/3 15:49
 */
@Configuration()
public class LifeCycleCallbacksConfiguration {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public LifeCycleCallbacksBean lifeCycleCallbacksBean() {
        return new LifeCycleCallbacksBean();
    }


}
