package com.zhy.spring.utils;

import com.zhy.model.git.entity.People;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

}
