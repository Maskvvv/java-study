package com.zhy.spring.generic.spring;


import org.junit.Test;
import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;

/**
 * {@link ResolvableType} Demo
 *
 * @see ResolvableType
 */
public class ResolvableTypeDemo {

    @Test
    public void test() {
        // 工厂创建
        // StringList <- ArrayList <- AbstractList <- List <- Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);

        resolvableType.getSuperType(); // ArrayList
        resolvableType.getSuperType().getSuperType(); // AbstractList

        System.out.println(resolvableType.asCollection().resolve()); // 获取 Raw Type
        System.out.println(resolvableType.asCollection().resolveGeneric(0)); // 获取泛型参数类型


    }

    private HashMap<Integer, List<String>> myMap;

    @Test
    public void example() throws NoSuchFieldException {
        ResolvableType t = ResolvableType.forField(ResolvableTypeDemo.class.getDeclaredField("myMap"));
        System.out.println(t.getSuperType()); // AbstractMap<Integer, List<String>>
        System.out.println(t.asMap()); // Map<Integer, List<String>>
        System.out.println(t.getGeneric(0)); // java.lang.Integer
        System.out.println(t.getGeneric(0).resolve()); // Integer
        System.out.println(t.getGeneric(1)); // List<String>
        System.out.println(t.getGeneric(1).resolve()); // List
        System.out.println(t.resolveGeneric(1, 0)); // String
    }
}
