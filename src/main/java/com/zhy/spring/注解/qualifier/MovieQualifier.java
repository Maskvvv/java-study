package com.zhy.spring.注解.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MovieQualifier {

    String value() default "";

//    String genre() default "";
//
//    Format format();
//
//    public enum Format {
//        VHS, DVD, BLURAY
//    }
}
