package com.zhy.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description: 收集(collect)
 * @author: zhouhongyin
 * @time: 2021/8/12 10:19
 */

public class StreamTest3 {

    /**
     * 归集(toList/toSet/toMap)
     */
    @Test
    public void streamTest1(){
        List<Integer> list = ListFactory.getIntegerList();
        System.out.println(list.stream().collect(Collectors.toList()));

        List<Integer> list1 = ListFactory.getIntegerList();
        System.out.println(list1.stream().collect(Collectors.toSet()));

        List<Integer> list2 = ListFactory.getIntegerList();
        TreeSet<Integer> treeSet = list2.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(treeSet);
    }

    /**
     * 接合(joining)
     */
    @Test
    public void streamTest2(){
        List<String> list = Arrays.asList(new String[]{"3","1","414","8","58"});

        String collect = list.stream().collect(Collectors.joining(","));
        System.out.println(collect);

        List<Integer> integerList = ListFactory.getIntegerList();
        String collect1 = integerList.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(collect1);
    }

    /**
     *  统计(count/averaging)
     */
    @Test
    public void streamTest3(){
        List<Integer> list = ListFactory.getIntegerList();
        System.out.println("求和方式一："+list.stream().collect(Collectors.summingInt(Integer::intValue)));

        List<Integer> list1 = ListFactory.getIntegerList();
        System.out.println("求和方式二："+ list1.stream().mapToInt(Integer::intValue).sum());

        List<Integer> list2 = ListFactory.getIntegerList();
        System.out.println("求最大值方式一："+list2.stream().collect(Collectors.maxBy(Integer::compareTo)));

        List<Integer> list3 = ListFactory.getIntegerList();
        System.out.println("求最大值方式二："+ list3.stream().max(Integer::compareTo));

        List<Integer> list4 = ListFactory.getIntegerList();
        System.out.println("求最大值方式一："+list4.stream().collect(Collectors.minBy(Integer::compareTo)));

        List<Integer> list5 = ListFactory.getIntegerList();
        System.out.println("求最小值方式二："+ list5.stream().min(Integer::compareTo));

        List<Integer> list6 = ListFactory.getIntegerList();
        IntSummaryStatistics collect = list6.stream().collect(Collectors.summarizingInt(Integer::intValue));
        System.out.println("聚合：" + collect);

    }

    @Test
    public void streamTest4(){
        List<People> peopleList = ListFactory.getPeopleList();
        Map<String, Integer> map = peopleList.stream().collect(Collectors.toMap(People::getName, People::getAge));
        System.out.println(map);

        List<People> peopleList1 = ListFactory.getPeopleList();
        Map<String, People> map1 = peopleList1.stream().collect(Collectors.toMap(People::getName, Function.identity()));
        System.out.println(map1);

        List<People> peopleList2 = ListFactory.getPeopleList();
        peopleList2.add(new People("tom",0,1));
        Map<String, People> map2 = peopleList2.stream().collect(Collectors.toMap(People::getName, Function.identity(),(nowValue, newValue)->newValue));
        System.out.println("有重复键值的情况："+map2);


        List<People> peopleList3 = ListFactory.getPeopleList();
        peopleList3.add(new People("tom",0,2));
        TreeMap<String, People> map3 = peopleList3.stream().collect(Collectors.toMap(People::getName, Function.identity(),(nowValue,newValue)->newValue,TreeMap::new));
        System.out.println("设置Map的类型："+map3);

    }
}
