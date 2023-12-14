package com.zhy.java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2021/8/10 15:27
 */

public class ListFactory {
    public static List<Integer> getIntegerList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(3);
        numbers.add(3);
        numbers.add(4);
        numbers.add(8);
        numbers.add(16);
        numbers.add(19);
        numbers.add(27);
        numbers.add(23);
        numbers.add(99);
        numbers.add(15);
        numbers.add(32);
        numbers.add(5);
        numbers.add(232);
        numbers.add(56);
        return numbers;
    }

    public static List<People> getPeopleList() {
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People("maik", 10, 1));
        peopleList.add(new People("jone1", 56, 1));
        peopleList.add(new People("jone2", 56, 3));
        peopleList.add(new People("tom1", 32, 2));
        peopleList.add(new People("tom2", 32, 3));
        peopleList.add(new People("tom3", 32, 4, 1));
        peopleList.add(new People("tom4", 32, 4, 2));
        peopleList.add(new People("tom5", 32, 4, 3));
        peopleList.add(new People("tom5", null, 4, 3));

        return peopleList;
    }

    public static List<People> getFlatPeopleList() {
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People(Arrays.asList(1, 2, 3)));
        peopleList.add(new People(Arrays.asList(3, 4, 5)));
        peopleList.add(new People(Arrays.asList(4, 5, 6)));
        peopleList.add(new People(null));
        peopleList.add(new People(new ArrayList<>()));
        return peopleList;
    }

}

