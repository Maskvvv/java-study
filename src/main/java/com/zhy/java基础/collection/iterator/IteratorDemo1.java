package com.zhy.java基础.collection.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhouhongyin
 * @since 2022/4/29 10:03
 */
public class IteratorDemo1 {

    public static void main(String[] args) {
        List<Integer> collect = Stream.of(1, 2, 3, 4, 5, 6).collect(Collectors.toList());

        Iterator<Integer> iterator = collect.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                iterator.remove();
            }

        }

        System.out.println(collect);

    }
}
