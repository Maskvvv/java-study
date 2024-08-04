package com.zhy.javabase.反射;

import lombok.Data;

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

        System.out.println("----------getDeclaredFields-----------");
        Field[] declaredFields = stdClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        System.out.println("----------getDeclaredFields-----------");

        System.out.println("----------getFields-----------");
        Field[] fields = stdClass.getFields();
        for (Field declaredField : fields) {
            System.out.println(declaredField);
        }
        System.out.println("----------getFields-----------");

        Object p = new Person("Xiao Ming");
        // 获取该类 Class 对象
        Class<? extends Object> c = p.getClass();
        // 获取该类 属性 Field
        Field f = c.getDeclaredField("name");
        // 通过 Field 后去该类的 实列的 属性的值
        Object value = f.get(p);
        System.out.println(value)  ; // "Xiao Ming"


        Student zhy = new Student("zhy");

        zhy.setScore(0);
        zhy.setGrade(0);

    }

}

@Data
class Student extends Person {
    public int score;
    private int grade;


    public Student(String name) {
        super(name);
    }
}

class Person {
    public String name;
    private String age;

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }


}
