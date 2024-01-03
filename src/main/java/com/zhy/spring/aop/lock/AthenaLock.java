package com.zhy.spring.aop.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhouhongyin
 * @since 2023/3/2 17:55
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AthenaLock {

    String prefix() default "";

    Class<? extends KeyConvert>[] keyConvert() default {};

    String spEl() default "";

    String keySeparator() default ":";

    long leaseTime() default 10 * 1000;

    Class<? extends AthenaLockProcessor>[] lockProcessor() default {};
}
