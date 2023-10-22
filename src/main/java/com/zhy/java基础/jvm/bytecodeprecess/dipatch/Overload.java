package com.zhy.java基础.jvm.bytecodeprecess.dipatch;

import java.io.Serializable;

public class Overload {
    public static void sayHello(Object arg) {
        System.out.println("hello Object");
    }

    public static void sayHello(int arg) {
        System.out.println("hello int");
    }

    public static void sayHello(long arg) {
        System.out.println("hello long");
    }

    public static void sayHello(Character arg) {
        System.out.println("hello Character");
    }

    public static void sayHello(char arg) {
        System.out.println("hello char");
    }

    public static void sayHello(char... arg) {
        System.out.println("hello char ...");
    }

    public static void sayHello(Serializable arg) {
        System.out.println("hello Serializable");
    }

    /**
     * 这很好理解，'a'是一个char类型的数据，自然会寻找参数类型为char的重载方法，如果注释掉
     * sayHello(char arg)方法，那输出会变为： hello int
     */
    public static void main(String[] args) {
        sayHello('a');
    }
}
