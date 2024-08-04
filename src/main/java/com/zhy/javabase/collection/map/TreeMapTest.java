package com.zhy.javabase.collection.map;

import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/19 16:07
 */
public class TreeMapTest {


    /**
     * 一致性 hash
     */
    @Test
    public void test1() {

        TreeMap<Integer, Integer> ring = new TreeMap<>();

        for (int i = 0; i < 10; i++) {
            ring.put(i, i);
        }

        SortedMap<Integer, Integer> tailMap = ring.tailMap(5);
        System.out.println(tailMap);

        Integer key = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();

        System.out.println(ring.get(key));

    }

}
