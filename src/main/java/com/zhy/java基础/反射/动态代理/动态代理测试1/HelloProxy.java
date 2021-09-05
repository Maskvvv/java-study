package com.zhy.java基础.反射.动态代理.动态代理测试1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloProxy implements InvocationHandler {
    private Object object;

    public HelloProxy(Object object) {
        this.object = object;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置方法！" + method.getName());
        method.invoke(object,args);
        System.out.println("后置方法！" + method.getName());
        return null;
    }
}
