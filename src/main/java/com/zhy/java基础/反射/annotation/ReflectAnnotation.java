package com.zhy.java基础.反射.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/26 17:24
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ReflectAnnotation {

    String name() default "";
}
