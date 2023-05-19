package com.zhy.spring.scan;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyRegistryDefinitionRegistrar.class)
public @interface EnableMyRegistry {

    String ATTRIBUTE_NAME_BASE_PACKAGES = "basePackages";

    String[] basePackages() default {};

}
