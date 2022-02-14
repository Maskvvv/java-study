package com.zhy.java基础.generic;

/**
 * @author zhouhongyin
 * @since 2022/2/14 11:18
 */
public class GenericTest<T> {

    private T key;

    public T test1(T key) {

        return key;
    }

    public <R> R test2(R key) {

        return key;
    }


}
