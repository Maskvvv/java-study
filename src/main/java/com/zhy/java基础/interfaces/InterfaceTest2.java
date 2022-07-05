package com.zhy.java基础.interfaces;

/**
 * @author zhouhongyin
 * @since 2022/7/5 14:23
 */
public class InterfaceTest2 implements InterfaceTest{
    @Override
    public void method1() {

    }

    @Override
    public void method2() {
        InterfaceTest.super.method2();
    }
}
