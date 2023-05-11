package com.zhy.spring.resource.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;

/**
 * @author zhouhongyin
 * @since 2023/5/6 14:27
 */
@Configuration
public class ResourceLoaderConfiguration {

    @Bean
    public ResourceLoader resourceLoader(ResourceLoader resourceLoader) {
        return resourceLoader;
    }

}
