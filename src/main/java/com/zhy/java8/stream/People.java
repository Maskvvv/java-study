package com.zhy.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class People {
    private String name;
    private Integer age;
    private Integer theClass;
    private Integer nullValue;
    private List<Integer> list;
    private final Random random = new Random();

    public People(String name, Integer age, Integer theClass) {
        this.name = name;
        this.age = age;
        this.theClass = theClass;
    }

    public People(String name, Integer age, Integer theClass, Integer nullValue) {
        this.name = name;
        this.age = age;
        this.theClass = theClass;
        this.nullValue = nullValue;
    }

    public People(List<Integer> list) {
        this.list = list;
    }
}
