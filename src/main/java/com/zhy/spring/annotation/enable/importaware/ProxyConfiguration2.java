package com.zhy.spring.annotation.enable.importaware;

import com.zhy.spring.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;


@Configuration
public class ProxyConfiguration2 implements ImportAware {

    private AnnotationAttributes info;

    @Bean
    public User user2() {
        User proxyMode = new User();
        //proxyMode.setName(info.getString("name") + 2);
        return proxyMode;
    }

    @Bean
    public User user3() {
        User proxyMode = new User();
        proxyMode.setName("name3");
        return proxyMode;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata importMetadata) {
        this.info = AnnotationAttributes.fromMap(
                importMetadata.getAnnotationAttributes(EnableProxy.class.getName(), false));
    }
}
