package com.zhy.spring.cache.commonCache.keyEnum;


import com.zhy.spring.cache.commonCache.CacheKey;

/**
 * 数据字典缓存key
 */
public enum DictCacheKey implements CacheKey {

    DICT_SUPPORTSTRANSITWAREHOUSEDELIVERYINFO("dict", "supportsTransitWarehouseDeliveryInfo"),
    DICT_TRANSITWAREHOUSEDELIVERYINFO("dict", "transitWarehouseDeliveryInfo"),


    ;
    private final String namespace;
    private final String key;

    DictCacheKey(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }

    public String namespace() {
        return namespace;
    }

    public String key() {
        return key;
    }
}