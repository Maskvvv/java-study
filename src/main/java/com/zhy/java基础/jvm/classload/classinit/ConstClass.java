package com.zhy.java基础.jvm.classload.classinit;

public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";
}
