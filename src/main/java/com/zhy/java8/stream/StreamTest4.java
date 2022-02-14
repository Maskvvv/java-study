package com.zhy.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description:分组分片
 * @author: zhouhongyin
 * @time: 2021/8/12 11:25
 */

public class StreamTest4 {

    /**
     * 分组
     */
    @Test
    public void streamTest1(){
        List<People> peopleList = ListFactory.getPeopleList();
        List<People> nullList = new ArrayList<>();
        nullList.stream().collect(Collectors.groupingBy(People::getAge))
                .forEach((key, value) -> System.out.println(key+":"+value));
    }

    /**
     * 分片
     */
    @Test
    public void streamTest2(){
        List<People> peopleList = ListFactory.getPeopleList();

        Map<Boolean, List<People>> collect = peopleList.stream().collect(Collectors.partitioningBy(people -> people.getAge() > 31));

        for (Map.Entry<Boolean, List<People>> booleanListEntry : collect.entrySet()) {
            System.out.println("key:" + booleanListEntry.getKey() + " value:" + booleanListEntry.getValue());
        }

    }


    /**
     * 扩展功能
     */
    @Test
    public void streamTest3(){
        // 计数
        List<People> list = ListFactory.getPeopleList();
        Map<Integer, Long> collect = list.stream().collect(Collectors.groupingBy(People::getAge, Collectors.counting()));
        System.out.println(collect);

        // 求和
        List<People> list1 = ListFactory.getPeopleList();
        Map<Integer, Integer> collect1 = list1.stream().collect(Collectors.groupingBy(People::getAge, Collectors.summingInt(People::getAge)));
        System.out.println(collect1);

        // 分组后集合中最大最小值
        List<People> list2 = ListFactory.getPeopleList();
        Map<Integer, Optional<People>> collect2 = list2.stream().collect(Collectors.groupingBy(People::getTheClass, Collectors.maxBy(Comparator.comparing(People::getAge))));
        System.out.println(collect2);

        List<People> list3 = ListFactory.getPeopleList();
        Map<Integer, Optional<People>> collect3 = list3.stream().collect(Collectors.groupingBy(People::getTheClass, Collectors.minBy(Comparator.comparing(People::getAge))));
        System.out.println(collect3);


    }

}
