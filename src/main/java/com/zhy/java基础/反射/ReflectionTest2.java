package com.zhy.java基础.反射;

import java.lang.reflect.Method;

/**
 * 获取方法
 */
public class ReflectionTest2 {
    public static void main(String[] args) throws Exception {
        Student2 student2 = new Student2();
        student2.setScore(10);

        Class<? extends Student2> aClass = student2.getClass();

        Method setScore = aClass.getDeclaredMethod("setScore", int.class);
        Object invoke1 = setScore.invoke(student2, 9);


        Method getScore = aClass.getDeclaredMethod("getScore");
        Object invoke = getScore.invoke(student2);
        System.out.println(invoke);

    }

}

class Student2 extends Person2 {
    public int score;
    private int grade;

    public Student2() {
        super();
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student2(String name) {
        super(name);
    }
}

class Person2 {
    public String name;
    public Person2(){

    }

    public Person2(String name) {
        this.name = name;
    }
}
