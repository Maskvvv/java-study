package com.zhy.java基础.collection.map.linkedmap;

import org.junit.jupiter.api.Test;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/19 16:19
 */
public class LinkedHashMapTest {

    /**
     * LRU
     */
    @Test
    public void test1() {

        LRUCache<Integer, Integer> lRUCache = new LRUCache<>();
        lRUCache.setMaxCapacity(5);

        for (int i = 0; i < 10; i++) {
            lRUCache.put(i, i);
        }

        lRUCache.put(6, 10);

        lRUCache.put(10, 10);

        System.out.println(lRUCache);

    }

}
