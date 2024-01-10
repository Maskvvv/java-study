package com.zhy.spring.annotation.enable.importaware;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ProxyConfiguration2.class)
public @interface EnableProxy {

    String name() default "mask";
}
