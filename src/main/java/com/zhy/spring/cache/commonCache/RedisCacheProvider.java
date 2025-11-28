package com.zhy.spring.cache.commonCache;

import com.alibaba.fastjson.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class RedisCacheProvider implements CacheProvider {
    private final StringRedisTemplate stringRedisTemplate;

    public <V> V get(String key, Class<V> type) {
        return get(key, (Type) type);
    }

    public <V> V get(String key, Type type) {
        String obj = stringRedisTemplate.opsForValue().get(key);
        if (obj == null) return null;
        try {
            return parse(obj, type);
        } catch (Exception e) {
            log.error("Redis反序列化失败, key:{}", key, e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private <V> V parse(String json, Type type) {
        if (type == String.class || (type instanceof Class && ((Class<?>) type) == String.class)) {
            String s = json;
            if (s.length() >= 2 && s.startsWith("\"") && s.endsWith("\"")) {
                return (V) JSON.parseObject(s, String.class);
            } else {
                return (V) s;
            }
        }
        return JSON.parseObject(json, type);
    }

    public <V> void put(String key, V value, long ttlSeconds) {
        String payload = JSON.toJSONString(value);
        if (ttlSeconds > 0) {
            stringRedisTemplate.opsForValue().set(key, payload, ttlSeconds, TimeUnit.SECONDS);
        } else {
            stringRedisTemplate.opsForValue().set(key, payload);
        }
    }

    public boolean evict(String key) {
        try {
            stringRedisTemplate.delete(key);
            return true;
        } catch (Exception e) {
            log.error("缓存删除失败", e);
            return false;
        }
    }

    public boolean contains(String key) {
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        return hasKey;
    }

    public long ttl(String key) {
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
        return expire;
    }

    public void clear() {
    }
}