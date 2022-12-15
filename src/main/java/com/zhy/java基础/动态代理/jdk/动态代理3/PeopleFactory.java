package com.zhy.java基础.动态代理.jdk.动态代理3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhouhongyin
 * @since 2022/12/15 15:54
 */
public class PeopleFactory implements InvocationHandler {

    private Object target;

    public PeopleFactory(Object target) {
        this.target = target;
    }

    public Object getPeople() {
        Object o = Proxy.newProxyInstance(People.class.getClassLoader(), new Class[]{People.class}, this);
        return o;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        method.invoke(target, args);
        System.out.println("after");

        return null;
    }
}
