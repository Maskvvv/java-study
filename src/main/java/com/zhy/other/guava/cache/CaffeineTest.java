package com.zhy.other.guava.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.zhy.model.git.entity.People;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p> Caffeine </p>
 *
 * @author zhouhongyin
 * @since 2023/7/31 10:15
 */
public class CaffeineTest {

    /**
     * 手动加载
     */
    @Test
    public void test1() {
        LoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(k -> {
                    System.out.println("generate value");
                    return "null";
                });

        People people = new People();

        cache.get(people.getName());
        cache.get(people.getName());
        cache.get(people.getName());

    }

    /**
     * 异步加载
     */
    public CompletableFuture<Object> asyncOperator(String key) {
        AsyncLoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .buildAsync(k -> setAsyncValue(key).get());

        CompletableFuture<Object> objectCompletableFuture = cache.get(key);
        return objectCompletableFuture;
    }

    public CompletableFuture<Object> setAsyncValue(String key) {
        return CompletableFuture.supplyAsync(() -> {
            return key + "value";
        });
    }

}
