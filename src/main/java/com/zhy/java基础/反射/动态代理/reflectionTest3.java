package com.zhy.java基础.反射.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class reflectionTest3 {
    public static void main(String[] args) {
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method);
                if (method.getName() == "morning") {
                    System.out.println("morning" + args[0]);
                }
                return null;

            }
        };

        Hello hello = (Hello) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Hello.class}, invocationHandler);
        hello.morning("zhy");
    }


}
interface Hello {
    void morning(String name);
}
