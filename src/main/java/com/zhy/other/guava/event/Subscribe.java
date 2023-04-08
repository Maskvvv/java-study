package com.zhy.other.guava.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Subscribe 是一个注解，用于标明观察者中的哪个函数可以接收消息。
 *
 * @author zhouhongyin
 * @since 2023/4/8 11:51
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
}
