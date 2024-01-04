package com.zhy.spring.utils;

import com.zhy.model.git.entity.People;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.Test;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>spring reflect utils</p>
 *
 * @author zhouhongyin
 * @since 2023/8/10 22:17
 */
public class ReflectionUtilsTest {

    @Test
    public void method() throws InvocationTargetException, IllegalAccessException {

        Method getName = ReflectionUtils.findMethod(People.class, "getName");
        Method setName = ReflectionUtils.findMethod(People.class, "setName", String.class);

        People people = new People();

        setName.invoke(people, "mike");
        Object name = getName.invoke(people);

        System.out.println(name);


    }

    /**
     * 方法过滤
     */
    @Test
    public void doWithMethods() throws ClassNotFoundException {
        String targetClassName = "com.zhy.spring.aop.study.common.EchoService";
        // 获取当前线程 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类
        Class<?> targetClass = classLoader.loadClass(targetClassName);

        // 查找方法  throws 类型为 NullPointerException
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("仅抛出 NullPointerException 方法为：" + method);
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                Class[] parameterTypes = method.getParameterTypes();
                Class[] exceptionTypes = method.getExceptionTypes();
                return parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0])
                        && exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });


        List<Method> methods = new ArrayList<>();
        ReflectionUtils.doWithMethods(targetClass, method -> {
            // Exclude pointcuts
            if (AnnotationUtils.getAnnotation(method, Pointcut.class) == null) {
                methods.add(method);
            }
        }, ReflectionUtils.USER_DECLARED_METHODS);
    }

}
