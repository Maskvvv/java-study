package com.zhy.javabase.动态代理.cglib;


import com.zhy.javabase.动态代理.modle.PeopleFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhouhongyin
 * @since 2022/12/15 16:05
 */
public class CglibPeopleFactory implements MethodInterceptor, PeopleFactory {

    private Object target;

    public CglibPeopleFactory(Object target) {
        this.target = target;
    }

    /**
     * @param o: 代理对象
     * @param method: 被代理方法
     * @param objects: 方法入参
     * @param methodProxy: CGLIB方法
     **/
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before");
        methodProxy.invokeSuper(o, objects);
        //method.invoke(o, objects);
        System.out.println("after");
        return null;
    }

    @Override
    public Object getPeople() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
