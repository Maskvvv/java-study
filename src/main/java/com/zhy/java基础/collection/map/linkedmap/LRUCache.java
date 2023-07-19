package com.zhy.java基础.collection.map.linkedmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/19 16:23
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int maxCapacity = 16;

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() >= maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
