package com.zhy.spring.cache.commonCache;

import java.lang.reflect.Type;

public interface CacheProvider {
    /**
     * 读取缓存值
     *
     * @param key 缓存键
     * @param type 目标类型
     * @return 命中返回值，未命中返回 null
     */
    <V> V get(String key, Class<V> type);

    /**
     * 读取缓存值（支持泛型类型）
     *
     * @param key 缓存键
     * @param type 泛型类型描述
     * @return 命中返回值，未命中返回 null
     */
    <V> V get(String key, Type type);

    /**
     * 写入缓存值
     *
     * @param key 缓存键
     * @param value 缓存值
     * @param ttlSeconds 过期时间（秒），≤0 表示不过期
     */
    <V> void put(String key, V value, long ttlSeconds);

    /**
     * 删除缓存
     *
     * @param key 缓存键
     * @return 是否删除成功
     */
    boolean evict(String key);

    /**
     * 判断键是否存在
     *
     * @param key 缓存键
     * @return true 表示存在；false 表示不存在
     */
    boolean contains(String key);

    /**
     * 查询剩余有效期（秒）
     *
     * @param key 缓存键
     * @return >0 剩余秒数；0/负数表示永久或不存在（由实现定义）
     */
    long ttl(String key);

    /**
     * 清空当前 Provider 管理的缓存
     */
    void clear();
}