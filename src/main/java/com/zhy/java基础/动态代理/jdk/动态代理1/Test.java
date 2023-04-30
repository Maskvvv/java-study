package com.zhy.java基础.动态代理.jdk.动态代理1;

import java.lang.reflect.Proxy;

public class Test {

    public static void main(String[] args) {
        Hello hello = new Hello();
        HelloProxy helloProxy = new HelloProxy(hello);

        HelloInterface helloInterface = (HelloInterface) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(),
                helloProxy);
        helloInterface.sayHello();
    }

}
