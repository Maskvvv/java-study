package com.zhy.java8.stream;

import org.junit.Test;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 过滤、映射、跳过，限制、去重、排序
 *
 * @author zhouhongyin
 * @since 2021/8/10 15:25
 */

public class StreamTest1 {

    /**
     * 过滤
     */
    @Test
    public void streamTest1() {
        List<Integer> list = ListFactory.getIntegerList();
        long count = list.stream().filter(number -> number > 20).count();
        System.out.println(count);

        list.stream().forEach(System.out::println);

    }

    /**
     * 过滤
     */
    @Test
    public void streamTest2() {
        List<Integer> list = ListFactory.getIntegerList();
        List<Integer> collect = list.stream().filter(number -> number > 20).collect(Collectors.toList());
        System.out.println(collect);


    }

    /**
     * 映射
     */
    @Test
    public void streamTest3() {
        List<Integer> list = ListFactory.getIntegerList();
        List<String> collect = list.stream().map(i -> String.valueOf(i)).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> list1 = ListFactory.getIntegerList();
        List<String> collect1 = list1.stream().map(String::valueOf).collect(Collectors.toList());
        System.out.println(collect1);

        List<People> peopleList = ListFactory.getPeopleList();
        List<String> collect2 = peopleList.stream().map(people -> people.getName()).collect(Collectors.toList());
        System.out.println(collect2);
    }

    /**
     * 映射不修改原集合对象的方式
     */
    @Test
    public void streamTest3_1() {

        List<People> peopleList = ListFactory.getPeopleList();

        System.out.println("增加后：" + peopleList);

        peopleList.stream().map(people -> {
            people.setAge(people.getAge() + 10);
            return people;
        }).collect(Collectors.toList());

        System.out.println("增加后：" + peopleList);
    }

    /**
     * 映射不修改原集合对象的方式
     */
    @Test
    public void streamTest3_2() {

        List<People> peopleList = ListFactory.getPeopleList();

        System.out.println("增加后：" + peopleList);

        List<People> collect = peopleList.stream().map(people -> {
            People people1 = new People(people.getName(), people.getAge() + 10, people.getTheClass());
            return people1;
        }).collect(Collectors.toList());

        System.out.println("增加后：" + peopleList);
    }

    /**
     * 映射flatMap
     */
    @Test
    public void streamTest3_3() {
        List<People> peopleList = ListFactory.getPeopleList();

        List<Integer> collect = peopleList.stream().flatMap(people -> people.getList().stream()).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


    /**
     * 跳过，限制
     */
    @Test
    public void streamTest4() {
        System.out.println("skip");
        List<Integer> list = ListFactory.getIntegerList();
        list.stream().skip(3).forEach(System.out::println);

        System.out.println("limit");
        List<Integer> list1 = ListFactory.getIntegerList();
        list1.stream().limit(3).forEach(System.out::println);
    }

    /**
     * 去重
     */
    @Test
    public void streamTest5() {
        List<Integer> list = ListFactory.getIntegerList();
        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 排序
     */
    @Test
    public void streamTest6() {
        List<Integer> list = ListFactory.getIntegerList();
        List<Integer> collect = list.stream().sorted((i1, i2) -> Integer.compare(i1, i2)).collect(Collectors.toList());
        System.out.println(collect);

        List<People> list1 = ListFactory.getPeopleList();
        List<People> collect1 = list1.stream().sorted(Comparator.comparing(i -> i.getAge())).collect(Collectors.toList());
        System.out.println(collect1);

        List<People> list2 = ListFactory.getPeopleList();

        list2 = list2.stream().sorted(Comparator.comparing(People::getAge, Comparator.reverseOrder()).thenComparing(People::getName, Collator.getInstance(Locale.CHINA))).collect(Collectors.toList());

    }

    @Test
    public void streamTest7() {
        List<Integer> list;

        Integer[] intArray = {1};
        Integer tem = intArray[0];
        System.out.println(tem);

        intArray[0] = 2;

        System.out.println(tem);

        System.out.println(intArray[0]);

        System.out.println("---------------------------------");
        Integer[] intArray2 = intArray;
        System.out.println(intArray2);
        System.out.println(intArray);
        intArray = null;
        System.out.println(intArray2);
        System.out.println(intArray);
        System.out.println("---------------------------------");


        List<People> peopleList = ListFactory.getPeopleList();

        People people = peopleList.get(0);

        System.out.println(people);

        peopleList.get(0).setName("指针测试");

        System.out.println(people);

        List<People> peopleList2 = peopleList;

        System.out.println(peopleList2);

        peopleList2 = null;

        System.out.println(peopleList2);


        System.out.println(Runtime.getRuntime().availableProcessors());

        //System.out.println(ListUtils.emptyIfNull(list));
    }
}
