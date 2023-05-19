package com.zhy.spring.scan;

import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 包扫描注册器
 *
 * @author zhouhongyin
 * @since 2023/5/19 15:45
 */
public class MyRegistryDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(importingClassMetadata.getAnnotationAttributes(EnableMyRegistry.class.getName()));
        if (annotationAttributes == null) {
            return;
        }

        String[] basePackages = annotationAttributes.getStringArray(EnableMyRegistry.ATTRIBUTE_NAME_BASE_PACKAGES);

        ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
        constructorArgumentValues.addIndexedArgumentValue(0, basePackages);

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MyRegistry.class);
        beanDefinition.setConstructorArgumentValues(constructorArgumentValues);
        beanDefinition.setInitMethodName("init");

        registry.registerBeanDefinition("registry", beanDefinition);
    }

}
