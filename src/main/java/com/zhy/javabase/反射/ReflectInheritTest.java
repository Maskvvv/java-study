package com.zhy.javabase.反射;

import com.zhy.javabase.反射.model.ReflectStudent;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author zhouhongyin
 * @since 2022/12/13 11:54
 */
public class ReflectInheritTest {

    @Test
    public void test() {
        Class<ReflectStudent> reflectStudentClass = ReflectStudent.class;

        Field[] declaredFields = reflectStudentClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }

        System.out.println("-----------------super--------------------");

        Class<? super ReflectStudent> superclass = reflectStudentClass.getSuperclass();
        Field[] superclassDeclaredFields = superclass.getDeclaredFields();
        for (Field superclassDeclaredField : superclassDeclaredFields) {
            System.out.println(superclassDeclaredField.getName());
        }

    }
}
