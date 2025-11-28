package com.zhy.spring.cache.commonCache;


import org.apache.commons.lang.StringUtils;

public final class CacheKeyUtils {
    private CacheKeyUtils() {}

    public static String toString(CacheKey key) {
        if (key == null || StringUtils.isBlank(key.namespace()) || StringUtils.isBlank(key.key())) {
            throw new RuntimeException("缓存Key非法");
        }
        return key.namespace() + ":" + key.key();
    }

    public static String toString(CacheKey key, Object... bizKeys) {
        String base = toString(key);
        if (bizKeys == null || bizKeys.length == 0) {
            return base;
        }
        StringBuilder sb = new StringBuilder(base);
        for (Object o : bizKeys) {
            if (o == null) { continue; }
            String s = String.valueOf(o).trim();
            if (StringUtils.isBlank(s)) { continue; }
            sb.append(":").append(s);
        }
        return sb.toString();
    }
}