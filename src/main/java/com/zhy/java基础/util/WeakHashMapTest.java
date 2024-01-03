package com.zhy.java基础.util;

import com.zhy.java基础.reference.Apple;

import java.util.WeakHashMap;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/3 17:09
 */
public class WeakHashMapTest {
    public static void main(String[] args) throws InterruptedException {


        WeakHashMap<String, Apple> weakHashMap = new WeakHashMap<>();

        Apple apple1 = new Apple("1");
        weakHashMap.put("a", apple1);

        weakHashMap.put("b", new Apple("2"));

        System.gc();
        Thread.sleep(5 * 1000);

        System.out.println(weakHashMap.get("a"));
        System.out.println(weakHashMap.get("b"));

    }
}
