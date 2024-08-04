package com.zhy.javabase.动态代理.jdk.动态代理1;

public class Hello implements HelloInterface {
    private String heName = "heName";

    public void sayHello() {
        System.out.println("hello zhy!");
    }
}
