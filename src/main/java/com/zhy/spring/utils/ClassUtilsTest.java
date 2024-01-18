package com.zhy.spring.utils;

import org.springframework.util.ClassUtils;

/**
 * ClassUtilsTest
 *
 * @author zhouhongyin
 * @since 2024/1/14 22:55
 */
public class ClassUtilsTest {


    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = ClassUtils.forName("java.lang", Thread.currentThread().getContextClassLoader());

    }
}
