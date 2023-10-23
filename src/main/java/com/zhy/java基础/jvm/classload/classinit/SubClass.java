package com.zhy.java基础.jvm.classload.classinit;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
