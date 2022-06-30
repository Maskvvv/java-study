package com.zhy.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 *  for 循环性能测试
 * @author zhouhongyin
 * @since 2021/8/12 11:25
 */

public class StreamTest5 {

    @Test
    public void streamTest1(){
        List<Map<Integer, Integer>> list = new ArrayList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        targetMap.put(1, 1);
        targetMap.put(2, 1);
        targetMap.put(3, 1);
        targetMap.put(4, 1);

        for (int i = 0; i < 1000000; i++) {
            list.add(new HashMap<>(targetMap));
        }

        long startTime;
        long endTime;

        //-------------------map 循环------------------------
        startTime = System.currentTimeMillis();

        Map<Integer, Integer> map2 = new HashMap<>();

        list.stream().map(item -> {
            item.forEach((k, v) -> {
                map2.put(k, map2.getOrDefault(k, 0) + v);
            });

            return item;
        }).collect(Collectors.toList());

        endTime = System.currentTimeMillis();
        System.out.println("map 循环" + (endTime - startTime));

        //------------------- map 循环------------------------


    }


    @Test
    public void streamTest2(){
        List<Map<Integer, Integer>> list = new ArrayList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        targetMap.put(1, 1);
        targetMap.put(2, 1);
        targetMap.put(3, 1);
        targetMap.put(4, 1);

        for (int i = 0; i < 1000000; i++) {
            list.add(new HashMap<>(targetMap));
        }

        long startTime;
        long endTime;

        //-------------------forEach 循环------------------------
        startTime = System.currentTimeMillis();

        Map<Integer, Integer> map1 = new HashMap<>();

        list.forEach(item -> {
            item.forEach((k, v) -> {
                map1.put(k, map1.getOrDefault(k, 0) + v);
            });
        });

        endTime = System.currentTimeMillis();
        System.out.println("forEach 循环" + (endTime - startTime));

        //------------------- forEach 循环------------------------

    }


    @Test
    public void streamTest3(){
        List<Map<Integer, Integer>> list = new ArrayList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        targetMap.put(1, 1);
        targetMap.put(2, 1);
        targetMap.put(3, 1);
        targetMap.put(4, 1);

        for (int i = 0; i < 1000000; i++) {
            list.add(new HashMap<>(targetMap));
        }

        long startTime;
        long endTime;

        //------------------- 并行流 forEach 循环------------------------
        startTime = System.currentTimeMillis();

        Map<Integer, Integer> map3 = new ConcurrentHashMap<>();

        list.parallelStream().forEach(item -> {
            item.forEach((k, v) -> {
                map3.put(k, map3.getOrDefault(k, 0) + v);
            });
        });

        //Thread.sleep(5000);

        System.out.println(map3.get(1));
        System.out.println(map3.get(2));
        System.out.println(map3.get(3));
        System.out.println(map3.get(4));
        endTime = System.currentTimeMillis();
        System.out.println("并行流 forEach 循环" + (endTime - startTime));

        //------------------- 并行流 forEach 循环------------------------

    }

    @Test
    public void streamTest4(){
        List<Map<Integer, Integer>> list = new ArrayList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        targetMap.put(1, 1);
        targetMap.put(2, 1);
        targetMap.put(3, 1);
        targetMap.put(4, 1);

        for (int i = 0; i < 1000000; i++) {
            list.add(new HashMap<>(targetMap));
        }

        long startTime;
        long endTime;

        //-------------------forEach 循环------------------------
        startTime = System.currentTimeMillis();

        Map<Integer, Integer> map1 = new HashMap<>();

        list.forEach(item -> {

            map1.put(1, map1.getOrDefault(1, 0) + item.get(1));
            map1.put(2, map1.getOrDefault(2, 0) + item.get(2));
            map1.put(3, map1.getOrDefault(3, 0) + item.get(3));
            map1.put(4, map1.getOrDefault(4, 0) + item.get(4));

        });

        endTime = System.currentTimeMillis();
        System.out.println("forEach 循环" + (endTime - startTime));

        //------------------- forEach 循环------------------------

    }

    @Test
    public void streamTest5(){
        List<Map<Integer, Integer>> list = new ArrayList<>();
        Map<Integer, Integer> targetMap = new HashMap<>();
        targetMap.put(1, 1);
        targetMap.put(2, 1);
        targetMap.put(3, 1);
        targetMap.put(4, 1);

        for (int i = 0; i < 1000000; i++) {
            list.add(new HashMap<>(targetMap));
        }

        long startTime;
        long endTime;

        //-------------------forEach 循环------------------------
        startTime = System.currentTimeMillis();

        Map<Integer, Integer> map1 = new HashMap<>();

        list.forEach(item -> {
            for (Map.Entry<Integer, Integer> integerIntegerEntry : item.entrySet()) {
                Integer k = integerIntegerEntry.getKey();
                Integer v = integerIntegerEntry.getValue();
                map1.put(k, map1.getOrDefault(k, 0) + v);
            }

        });

        endTime = System.currentTimeMillis();
        System.out.println("forEach 循环" + (endTime - startTime));

        //------------------- forEach 循环------------------------

    }





}
