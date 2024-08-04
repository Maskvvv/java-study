package com.zhy.javabase.juc.threadlocal;

import java.lang.ref.WeakReference;

public class Salad extends WeakReference<Apple> {
    private Object value;

    public Salad(Apple apple, Object v) {
        super(apple);
        value = v;
    }

    public Object getValue() {
        return value;
    }
}
