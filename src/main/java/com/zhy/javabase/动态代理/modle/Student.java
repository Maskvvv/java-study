package com.zhy.javabase.动态代理.modle;

/**
 * @author zhouhongyin
 * @since 2022/12/15 15:52
 */
public class Student implements People{

    private String name;

    private Integer age;

    @Override
    public String say() {
        String say = "I'm a student";
        System.out.println(say);
        return say;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
