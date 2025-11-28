package com.zhy.spring.cache.commonCache;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通用缓存管理器，封装多层缓存读写与加载策略
 * <p>
 * - 支持多 Provider 级联读取（命中即返回）
 * - 支持并发下的加载合并，避免缓存击穿
 * - 支持命中后异步刷新，提高热点数据新鲜度
 * - 支持枚举 Key + 业务参数组合，统一命名空间管理
 */
@Slf4j
public class CommonCacheManager {
    private final List<CacheProvider> providers;
    private final ConcurrentHashMap<String, CompletableFuture<Object>> loading = new ConcurrentHashMap<>();

    /**
     * 构造函数
     *
     * @param providers 缓存提供者列表（按顺序读取），不能为空
     * @throws RuntimeException 当 providers 为空时抛出参数异常
     */
    public CommonCacheManager(List<CacheProvider> providers) {
        if (providers == null || providers.isEmpty()) {
            throw new RuntimeException("缓存Provider不能为空");
        }
        this.providers = providers;
    }

    /**
     * 读取缓存，按 Provider 顺序依次命中返回
     *
     * @param key 缓存键
     * @return 命中返回值；未命中返回 null
     */
    public <V> V get(String key, Class<V> type) {
        return get(key, (Type) type);
    }

    /**
     * 读取缓存（支持泛型类型），按 Provider 顺序依次命中返回
     *
     * @param key  缓存键
     * @param type 目标泛型类型（如 List<User>）
     * @return 命中返回值；未命中返回 null
     */
    public <V> V get(String key, Type type) {

        for (CacheProvider p : providers) {
            V v = p.get(key, type);
            if (v != null) return v;
        }
        return null;
    }

    /**
     * 使用枚举 Key 读取缓存
     *
     * @param key 枚举 Key（namespace + 静态 key）
     * @return 命中返回值；未命中返回 null
     */
    @SuppressWarnings("unchecked")
    public <V> V get(CacheKey key, Class<V> type) {
        return get(CacheKeyUtils.toString(key), (Type) type);
    }

    /**
     * 使用枚举 Key 读取缓存（支持泛型类型）
     *
     * @param key 枚举 Key
     * @param type 目标泛型类型
     * @return 命中返回值；未命中返回 null
     */
    public <V> V get(CacheKey key, Type type) {
        return get(CacheKeyUtils.toString(key), type);
    }

    /**
     * 使用枚举 Key + 业务参数读取缓存
     * 最终 key 格式：namespace:key:biz1:biz2...
     *
     * @param key 枚举 Key
     * @param bizKeys 业务参数（可变长，null/空白将被忽略）
     * @return 命中返回值；未命中返回 null
     */
    @SuppressWarnings("unchecked")
    public <V> V get(CacheKey key, Class<V> type, Object... bizKeys) {
        return get(CacheKeyUtils.toString(key, bizKeys), (Type) type);
    }

    /**
     * 使用枚举 Key + 业务参数读取缓存（支持泛型类型）
     *
     * @param key 枚举 Key
     * @param type 目标泛型类型
     * @param bizKeys 业务参数
     * @return 命中返回值；未命中返回 null
     */
    public <V> V get(CacheKey key, Type type, Object... bizKeys) {
        return get(CacheKeyUtils.toString(key, bizKeys), type);
    }


    /**
     * 写入缓存至所有 Provider
     *
     * @param key        缓存键
     * @param value      缓存值
     * @param ttlSeconds 过期时间（秒），≤0 表示不过期
     */
    public <V> void put(String key, V value, long ttlSeconds) {
        for (CacheProvider p : providers) {
            try {
                p.put(key, value, ttlSeconds);
            } catch (Exception e) {
                log.error("缓存写入失败", e);
            }
        }
    }

    /**
     * 使用枚举 Key 写入缓存
     *
     * @param key 枚举 Key
     * @param value 缓存值
     * @param ttlSeconds 过期时间（秒），≤0 表示不过期
     */
    @SuppressWarnings("unchecked")
    public <V> void put(CacheKey key, V value, long ttlSeconds) {
        put(CacheKeyUtils.toString(key), value, ttlSeconds);
    }

    /**
     * 使用枚举 Key + 业务参数写入缓存
     *
     * @param key 枚举 Key
     * @param value 缓存值
     * @param ttlSeconds 过期时间（秒），≤0 表示不过期
     * @param bizKeys 业务参数（可变长）
     */
    @SuppressWarnings("unchecked")
    public <V> void put(CacheKey key, V value, long ttlSeconds, Object... bizKeys) {
        put(CacheKeyUtils.toString(key, bizKeys), value, ttlSeconds);
    }

    /**
     * 删除缓存（所有 Provider）
     *
     * @param key 缓存键
     * @return 是否全部删除成功
     */
    public boolean evict(String key) {
        boolean ok = true;
        for (CacheProvider p : providers) {
            ok = p.evict(key) && ok;
        }
        return ok;
    }

    /**
     * 使用枚举 Key 删除缓存
     *
     * @param key 枚举 Key
     * @return 是否删除成功
     */
    @SuppressWarnings("unchecked")
    public boolean evict(CacheKey key) {
        return evict(CacheKeyUtils.toString(key));
    }

    /**
     * 使用枚举 Key + 业务参数删除缓存
     *
     * @param key 枚举 Key
     * @param bizKeys 业务参数
     * @return 是否删除成功
     */
    @SuppressWarnings("unchecked")
    public boolean evict(CacheKey key, Object... bizKeys) {
        return evict(CacheKeyUtils.toString(key, bizKeys));
    }

    /**
     * 读取或加载：未命中时调用 loader 加载，并写入缓存
     * 命中时可根据 Options 进行异步刷新
     * <p>
     * 并发场景下，同一 key 的加载合并，避免击穿
     *
     * @param key     缓存键
     * @param loader  加载器函数（按 key 加载值）
     * @param options 加载与写入选项；为 null 时使用默认选项
     * @return 缓存值或加载值（可能为 null）
     * @throws RuntimeException 参数或系统异常时抛出
     */
    public <V> V getOrLoad(String key, CacheLoader<String, V> loader, CacheOptions options, Class<V> type) {
        return getOrLoad(key, loader, options, (Type) type);
    }

    /**
     * 读取或加载（支持泛型类型）：未命中时调用 loader 加载，并写入缓存；命中时可根据 options 异步刷新
     * <p>
     * 并发场景下，同一 key 的加载通过 CompletableFuture 合并，避免缓存击穿
     *
     * @param key     缓存键
     * @param loader  加载器函数（按 key 加载值）
     * @param options 加载与写入选项；为 null 时使用默认选项
     * @param type    目标泛型类型
     * @return 缓存值或加载值（可能为 null）
     * @throws RuntimeException 参数为空或系统异常时抛出
     */
    public <V> V getOrLoad(String key, CacheLoader<String, V> loader, CacheOptions options, Type type) {

        if (key == null || loader == null) {
            throw new RuntimeException("参数不能为空");
        }
        if (options == null) options = CacheOptions.builder().build();
        final CacheOptions opts = options;
        V v = get(key, type);
        if (v != null) {
            if (opts.refreshAsync()) {
                CompletableFuture.runAsync(() -> {
                    try {
                        V nv = loader.load(key);
                        if (nv != null) put(key, nv, opts.ttlSeconds());
                    } catch (Exception e) {
                        log.error("缓存异步刷新失败", e);
                    }
                });
            }
            return v;
        }
        CompletableFuture<Object> future = loading.computeIfAbsent(key, k -> {
            CompletableFuture<Object> f = new CompletableFuture<>();
            CompletableFuture.supplyAsync(() -> {
                try {
                    V loaded = loader.load(k);
                    if (loaded != null) put(k, loaded, opts.ttlSeconds());
                    return loaded;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }).whenComplete((res, ex) -> {
                try {
                    if (ex != null) {
                        f.completeExceptionally(ex);
                    } else {
                        f.complete(res);
                    }
                } finally {
                    loading.remove(k);
                }
            });
            return f;
        });
        try {
            Object result = future.get();
            @SuppressWarnings("unchecked") V cast = (V) result;
            return cast;
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("加载缓存中断");
        } catch (Exception e) {
            throw new RuntimeException("加载缓存失败");
        }
    }

    /**
     * 读取或加载（默认 Options，Class 类型）
     *
     * @param key    缓存键
     * @param loader 加载器函数
     * @param type   目标类型
     * @return 缓存值或加载值
     */
    public <V> V getOrLoad(String key, CacheLoader<String, V> loader, Class<V> type) {
        return getOrLoad(key, loader, CacheOptions.builder().build(), (Type) type);
    }

    /**
     * 读取或加载（默认 Options，支持泛型类型）
     *
     * @param key 缓存键
     * @param loader 加载器函数
     * @param type 目标泛型类型
     * @return 缓存值或加载值
     */
    public <V> V getOrLoad(String key, CacheLoader<String, V> loader, Type type) {
        return getOrLoad(key, loader, CacheOptions.builder().build(), type);
    }

    /**
     * 使用枚举 Key + 业务参数读取或加载（默认 Options）
     *
     * @param key 枚举 Key
     * @param loader 加载器函数
     * @param bizKeys 业务参数
     * @return 缓存值或加载值
     */
    @SuppressWarnings("unchecked")
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, Class<V> type, Object... bizKeys) {
        return getOrLoad(CacheKeyUtils.toString(key, bizKeys), loader, CacheOptions.builder().build(), (Type) type);
    }

    /**
     * 使用枚举 Key + 业务参数读取或加载（默认 Options，支持泛型类型）
     *
     * @param key 枚举 Key
     * @param loader 加载器函数
     * @param type 目标泛型类型
     * @param bizKeys 业务参数
     * @return 缓存值或加载值
     */
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, Type type, Object... bizKeys) {
        return getOrLoad(CacheKeyUtils.toString(key, bizKeys), loader, CacheOptions.builder().build(), type);
    }

    /**
     * 使用枚举 Key 读取或加载
     *
     * @param key 枚举 Key
     * @param loader 加载器函数（按最终字符串 key 加载）
     * @param options 加载与写入选项
     * @return 缓存值或加载值
     */
    @SuppressWarnings("unchecked")
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, CacheOptions options, Class<V> type) {
        return getOrLoad(CacheKeyUtils.toString(key), loader, options, (Type) type);
    }

    /**
     * 使用枚举 Key 读取或加载（支持泛型类型）
     *
     * @param key 枚举 Key
     * @param loader 加载器函数
     * @param options 加载与写入选项
     * @param type 目标泛型类型
     * @return 缓存值或加载值
     */
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, CacheOptions options, Type type) {
        return getOrLoad(CacheKeyUtils.toString(key), loader, options, type);
    }

    /**
     * 使用枚举 Key 读取或加载（默认 Options）
     *
     * @param key 枚举 Key
     * @param loader 加载器函数
     * @return 缓存值或加载值
     */
    @SuppressWarnings("unchecked")
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, Class<V> type) {
        return getOrLoad(CacheKeyUtils.toString(key), loader, CacheOptions.builder().build(), (Type) type);
    }

    /**
     * 使用枚举 Key 读取或加载（默认 Options，支持泛型类型）
     *
     * @param key 枚举 Key
     * @param loader 加载器函数
     * @param type 目标泛型类型
     * @return 缓存值或加载值
     */
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, Type type) {
        return getOrLoad(CacheKeyUtils.toString(key), loader, CacheOptions.builder().build(), type);
    }

    /**
     * 使用枚举 Key + 业务参数读取或加载
     *
     * @param key 枚举 Key
     * @param loader 加载器函数（按最终字符串 key 加载）
     * @param options 加载与写入选项
     * @param bizKeys 业务参数
     * @return 缓存值或加载值
     */
    @SuppressWarnings("unchecked")
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, CacheOptions options, Class<V> type, Object... bizKeys) {
        return getOrLoad(CacheKeyUtils.toString(key, bizKeys), loader, options, (Type) type);
    }

    /**
     * 使用枚举 Key + 业务参数读取或加载（支持泛型类型）
     *
     * @param key 枚举 Key
     * @param loader 加载器函数
     * @param options 加载与写入选项
     * @param type 目标泛型类型
     * @param bizKeys 业务参数
     * @return 缓存值或加载值
     */
    public <V> V getOrLoad(CacheKey key, CacheLoader<String, V> loader, CacheOptions options, Type type, Object... bizKeys) {
        return getOrLoad(CacheKeyUtils.toString(key, bizKeys), loader, options, type);
    }

}