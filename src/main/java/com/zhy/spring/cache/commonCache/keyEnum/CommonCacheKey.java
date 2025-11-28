package com.zhy.spring.cache.commonCache.keyEnum;


import com.zhy.spring.cache.commonCache.CacheKey;

public enum CommonCacheKey implements CacheKey {




    ;
    private final String namespace;
    private final String key;

    CommonCacheKey(String namespace, String key) {
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