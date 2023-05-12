package com.zhy.java基础.collection.compare;

import org.junit.Test;

import java.util.Set;
import java.util.TreeMap;

/**
 * Comparable
 *
 * @author zhouhongyin
 * @since 2023/5/11 21:41
 */
public class MyComparable {

    /**
     * key 实现comparable
     */
    @Test
    public void test1() {
        TreeMap<PeopleCompareModel, String> map = new TreeMap<>();

        map.put(new PeopleCompareModel("2", 2), "2");
        map.put(new PeopleCompareModel("1", 1), "1");

        Set<PeopleCompareModel> keySet = map.keySet();

        keySet.forEach(System.out::println);
    }

    /**
     * set 构造方法使用 comparator
     */
    @Test
    public void test2() {
        TreeMap<PeopleCompareModel, String> map = new TreeMap<>(((o1, o2) -> -o1.getAge().compareTo(o2.getAge())));

        map.put(new PeopleCompareModel("2", 2), "2");
        map.put(new PeopleCompareModel("1", 1), "1");

        Set<PeopleCompareModel> keySet = map.keySet();

        keySet.forEach(System.out::println);
    }

}
