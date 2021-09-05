package com.zhy.java8.stream;

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/11 8:34
 */

public class StreamTest2 {
    @Test
    public void streamTest1(){
        List<Integer> list = ListFactory.getIntegerList();
        Integer max = list.stream().max(Integer::compare).get();
        System.out.println(max);

        List<Integer> list1 = ListFactory.getIntegerList();
        Integer min = list1.stream().min(Integer::compare).get();
        System.out.println(min);

        List<People> peopleList = ListFactory.getPeopleList();
        Optional<People> people = peopleList.stream().max(Comparator.comparing(People::getAge));
        System.out.println(people.get());

    }

    @Test
    public void streamTest2(){
        List<Integer> list = ListFactory.getIntegerList();

        Optional<Integer> first = list.stream().filter(i -> i > 100).findFirst();
        Integer integer = first.orElse(0);
        System.out.println(integer);

    }

    @Test
    public void streamTest3(){
        List<Integer> list = ListFactory.getIntegerList();

        Integer integer = list.stream().findAny().get();
        System.out.println(integer);
    }

    @Test
    public void streamTest4(){
        List<Integer> list = ListFactory.getIntegerList();
        boolean flag = list.stream().anyMatch(i -> i > 100);
        System.out.println(flag);

        List<Integer> list1 = ListFactory.getIntegerList();
        boolean flag1 = list1.stream().allMatch(i -> i > 100);
        System.out.println(flag1);
    }

    @Test
    public void streamTest5(){
        List<Integer> list = ListFactory.getIntegerList();
        Integer sum = list.stream().reduce((i1, i2) -> i1 + i2).get();
        System.out.println(sum);

        List<Integer> list1 = ListFactory.getIntegerList();
        Integer sum1 = list1.stream().reduce(Integer::sum).get();
        System.out.println(sum1);

        List<Integer> list2 = ListFactory.getIntegerList();
        Integer sum2 = list2.stream().reduce(1, Integer::sum);
        System.out.println(sum2);

    }

}
