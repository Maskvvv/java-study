package com.zhy.spring.annotation.enable.importaware;

import com.zhy.spring.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


@Configuration
public class ProxyConfiguration1 implements ImportAware {

    private AnnotationAttributes info;

    @Bean
    public User user() {
        User proxyMode = new User();
        proxyMode.setName("name");
        return proxyMode;
    }

    @Bean
    public User user1() {
        User proxyMode = new User();
        proxyMode.setName("name1");
        return proxyMode;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.info = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableProxy.class.getName(), false));
    }
}
