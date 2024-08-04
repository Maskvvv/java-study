package com.zhy.javabase.注解.注解测试1;

import com.zhy.javabase.注解.Report;

@Report("Person")
public class Person {

    @Range(min = 1,max = 20)
    public String name;

    @Range(max = 10)
    public String city;

    @Report("getSth")
    public String getSth(String str) {
        return str;
    }

}
