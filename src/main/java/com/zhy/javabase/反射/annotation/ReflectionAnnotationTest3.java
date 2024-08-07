package com.zhy.javabase.反射.annotation;

/**
 * 获取方法
 * @author a
 */
public class ReflectionAnnotationTest3 {
    public static void main(String[] args) throws Exception {
        Class<Student3> student3Class = Student3.class;
        if (student3Class.isAnnotationPresent(ReflectAnnotation.class)) {
            ReflectAnnotation annotation = student3Class.getAnnotation(ReflectAnnotation.class);
            String name = annotation.name();
            System.out.println(name);
        }

        ReflectAnnotation annotation = student3Class.getAnnotation(ReflectAnnotation.class);
        System.out.println(annotation);


    }

}

class Student3 extends Person3 {
    @ReflectAnnotation(name = "score")
    public int score;
    private int grade;

    public Student3() {
        super();
    }

    public int getScore(){
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Student3(String name) {
        super(name);
    }
}

class Person3 {
    public String name;
    public Person3(){

    }

    public Person3(String name) {
        this.name = name;
    }
}
