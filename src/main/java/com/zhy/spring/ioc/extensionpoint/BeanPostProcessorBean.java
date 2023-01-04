package com.zhy.spring.ioc.extensionpoint;

import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2023/1/4 14:01
 */
@Component
public class BeanPostProcessorBean {
    private String name;

    private Integer age;

    public BeanPostProcessorBean() {
        this.name = "mike";
        this.age = 1;
    }

    @Override
    public String toString() {
        return "BeanPostProcessorBean{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
