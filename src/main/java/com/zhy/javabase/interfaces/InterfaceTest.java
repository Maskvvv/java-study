package com.zhy.javabase.interfaces;

/**
 * @author zhouhongyin
 * @since 2022/7/5 14:19
 */
public interface InterfaceTest {

    void method1();

    default void method2() {
        System.out.println("ennennene");
    }

    static void method3() {
        System.out.println("11111");
    }
}
