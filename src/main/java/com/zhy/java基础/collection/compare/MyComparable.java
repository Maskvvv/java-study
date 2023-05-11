package com.zhy.java基础.collection.compare;

import java.util.Set;
import java.util.TreeMap;

/**
 * @author zhouhongyin
 * @since 2023/5/11 21:41
 */
public class MyComparable {

    public static void main(String[] args) {

        TreeMap<PeopleCompareModel, String> map = new TreeMap<>();

        map.put(new PeopleCompareModel("2", 2), "2");
        map.put(new PeopleCompareModel("1", 1), "1");

        Set<PeopleCompareModel> keySet = map.keySet();

        keySet.forEach(System.out::println);

    }

}
