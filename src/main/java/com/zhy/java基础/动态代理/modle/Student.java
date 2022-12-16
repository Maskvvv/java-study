package com.zhy.java基础.动态代理.modle;

/**
 * @author zhouhongyin
 * @since 2022/12/15 15:52
 */
public class Student implements People{
    @Override
    public String say() {
        String say = "I'm a student";
        System.out.println(say);
        return say;
    }
}
