package com.zhy.javabase.jvm.classload.classinit;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
