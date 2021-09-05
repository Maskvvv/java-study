package com.zhy.java基础.注解.注解测试1;

public class Person {

    @Range(min = 1,max = 20)
    public String name;

    @Range(max = 10)
    public String city;

}
