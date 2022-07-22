package com.zhy.java基础.注解.注解测试1;

import com.zhy.java基础.注解.Report;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author zhouhongyin
 * @since 2022/7/8 11:38
 */
public class AnnotationTest {

    @Test
    public void annotationTest() throws Exception {
        // 判断@Report是否存在于Person类:
        System.out.println(Person.class.isAnnotationPresent(Report.class));

        Class<Person> personClass = Person.class;

        Method getSth = personClass.getMethod("getSth", String.class);
        System.out.println(getSth.isAnnotationPresent(Report.class));

        String ennene = (String) getSth.invoke(new Person(), "ennene");
        System.out.println(ennene);


    }

}
