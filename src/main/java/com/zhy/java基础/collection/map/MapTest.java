package com.zhy.java基础.collection.map;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 线程安全的 Map
 *
 *
 * @author zhouhongyin
 * @since 2023/2/16 15:19
 */
public class MapTest {

    /**
     * ConcurrentHashMap：无序； key 不允许为空
     * ConcurrentSkipListMap：
     *      有序； key 不允许为空
     *      内部是跳表实现有序，跳表插入、删除、查询操作平均的时间复杂度是 O(log n)
     *
     */
    @Test
    public void concurrentMap() {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();


        ConcurrentSkipListMap<String, String> concurrentSkipListMap = new ConcurrentSkipListMap<>();
    }

}
