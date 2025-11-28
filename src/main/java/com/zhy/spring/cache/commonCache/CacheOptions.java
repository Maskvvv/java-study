package com.zhy.spring.cache.commonCache;

/**
 * 缓存选项配置
 * <p>
 * 包含：
 * - ttlSeconds：过期时间（秒），≤0 表示不过期
 * - refreshAsync：命中后是否异步刷新（提升热点数据新鲜度）
 * - namespace：命名空间（用于区分业务域）
 */
public class CacheOptions {
    /**
     * 过期时间（秒），≤0 表示不过期
     */
    private final long ttlSeconds;
    /**
     * 命中后是否异步刷新
     */
    private final boolean refreshAsync;
    /**
     * 键命名空间
     */
    private final String namespace;

    /**
     * 私有构造函数，仅通过 builder 创建
     *
     * @param ttlSeconds   过期时间（秒）
     * @param refreshAsync 是否异步刷新
     * @param namespace    命名空间
     */
    private CacheOptions(long ttlSeconds, boolean refreshAsync, String namespace) {
        this.ttlSeconds = ttlSeconds;
        this.refreshAsync = refreshAsync;
        this.namespace = namespace;
    }

    /**
     * 创建 builder
     *
     * @return Builder 实例
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * 获取过期时间（秒）
     */
    public long ttlSeconds() {
        return ttlSeconds;
    }

    /**
     * 是否在命中后异步刷新
     */
    public boolean refreshAsync() {
        return refreshAsync;
    }

    /**
     * 获取命名空间
     */
    public String namespace() {
        return namespace;
    }

    /**
     * 构建器：提供默认值与链式配置
     * 默认：ttl=60s、refreshAsync=true、namespace="default"
     */
    public static class Builder {
        private long ttlSeconds = 60;
        private boolean refreshAsync = false;
        private String namespace = "default";

        /**
         * 设置过期时间（秒）
         */
        public Builder ttlSeconds(long ttl) {
            this.ttlSeconds = ttl;
            return this;
        }

        /**
         * 设置是否命中后异步刷新
         */
        public Builder refreshAsync(boolean refreshAsync) {
            this.refreshAsync = refreshAsync;
            return this;
        }

        /**
         * 设置命名空间
         */
        public Builder namespace(String ns) {
            this.namespace = ns;
            return this;
        }

        /**
         * 构建 CacheOptions 实例
         */
        public CacheOptions build() {
            return new CacheOptions(ttlSeconds, refreshAsync, namespace);
        }
    }
}