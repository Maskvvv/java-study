package com.zhy.java8.stream;

import org.apache.commons.collections4.ListUtils;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  
 * @author zhouhongyin
 * @since 2021/8/10 15:25
 */

public class StreamTest1 {

    @Test
    public void streamTest1(){
        List<Integer> list = ListFactory.getIntegerList();
        long count = list.stream().filter(number -> number > 20).count();
        System.out.println(count);

        list.stream().forEach(System.out::println);

    }

    @Test
    public void streamTest2(){
        List<Integer> list = ListFactory.getIntegerList();
        List<Integer> collect = list.stream().filter(number -> number > 20).collect(Collectors.toList());
        System.out.println(collect);


    }

    @Test
    public void streamTest3(){
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
    public void streamTest3_1(){

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
    public void streamTest3_2(){

        List<People> peopleList = ListFactory.getPeopleList();

        System.out.println("增加后："+peopleList);

        List<People> collect = peopleList.stream().map(people -> {
            People people1 = new People(people.getName(), people.getAge() + 10, people.getTheClass());
            return people1;
        }).collect(Collectors.toList());

        System.out.println("增加后："+peopleList);
    }

    @Test
    public void streamTest4(){
        List<Integer> list = ListFactory.getIntegerList();
        list.stream().skip(3).forEach(System.out::println);

        List<Integer> list1 = ListFactory.getIntegerList();
        list1.stream().limit(3).forEach(System.out::println);
    }

    @Test
    public void streamTest5(){
        List<Integer> list = ListFactory.getIntegerList();
        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void streamTest6(){
        List<Integer> list = ListFactory.getIntegerList();
        List<Integer> collect = list.stream().sorted((i1, i2) -> Integer.compare(i1, i2)).collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> list1 = ListFactory.getIntegerList();
        List<Integer> collect1 = list1.stream().sorted(Comparator.comparingInt(i -> i)).collect(Collectors.toList());
        System.out.println(collect1);
    }

    @Test
    public void streamTest7(){
        List<Integer> list;

        Integer[] intArray = {1};
        Integer tem = intArray[0];
        System.out.println(tem);

        intArray[0] =2;

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
