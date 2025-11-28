package com.zhy.spring.cache.commonCache;

/**
 * 缓存加载器函数式接口
 *
 * 说明：
 * - 该接口用于在缓存未命中时按 Key 加载数据源中的值，通常与
 *   {@link CommonCacheManager#getOrLoad(String, CacheLoader, cn.com.hicmall.common.cache.CacheOptions, java.lang.reflect.Type)}
 *   搭配使用。
 */
@FunctionalInterface
public interface CacheLoader<K, V> {
    /**
     * 根据 Key 加载数据源中的值
     *
     * @param key 加载所需的唯一键
     * @return 加载到的值；若不存在则返回 null
     */
    V load(K key);
}