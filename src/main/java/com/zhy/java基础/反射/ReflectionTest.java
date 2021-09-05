package com.zhy.java基础.反射;

import java.lang.reflect.Field;

/**
 * 获取属性
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        Class stdClass = Student.class;
        // 获取public字段"score":
        System.out.println(stdClass.getField("score"));
        // 获取继承的public字段"name":
        System.out.println(stdClass.getField("name"));
        // 获取private字段"grade":
        System.out.println(stdClass.getDeclaredField("grade"));


        Object p = new Person("Xiao Ming");
        // 获取该类 Class 对象
        Class c = p.getClass();
        // 获取该类 属性 Field
        Field f = c.getDeclaredField("name");
        // 通过 Field 后去该类的 实列的 属性的值
        Object value = f.get(p);
        System.out.println(value); // "Xiao Ming"
    }

}

class Student extends Person {
    public int score;
    private int grade;


    public Student(String name) {
        super(name);
    }
}

class Person {
    public String name;

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }
}
