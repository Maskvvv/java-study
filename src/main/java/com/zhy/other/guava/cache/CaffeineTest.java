package com.zhy.other.guava.cache;

import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.zhy.model.git.entity.People;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
     * <P>
     * LoadingCache key 不能为空
     */
    @Test
    public void loadingCacheNullKey() {
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
     * 手动加载
     * <P>
     * LoadingCache value 返回空即未命中缓存，所以需要注意缓存穿透
     */
    @Test
    public void loadingCacheNullValue() {
        LoadingCache<String, Object> cache = Caffeine.newBuilder()
                .maximumSize(100).recordStats()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(k -> {
                    System.out.println("generate value");
                    return null;
                });

        cache.get("key1");
        cache.get("key1");
        cache.get("key1");

        System.out.println(cache.stats());
    }

    /**
     * 异步加载
     */
    @Test
    public void asyncOperator() throws ExecutionException, InterruptedException {
        DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory("mypool");

        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        AsyncLoadingCache<String, String> cache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .executor(threadPool)
                //.buildAsync(k -> setAsyncValue(k).get());
                .buildAsync((k, executor) -> {
                    System.out.println("async executor current thread: " + Thread.currentThread().getName());

                    return CompletableFuture.supplyAsync(() -> {
                        System.out.println("supplyAsync executor current thread: " + Thread.currentThread().getName());
                       return  "this key is " + k;
                    }, threadPool);
                });

        CompletableFuture<String> future = cache.get("key");

        future.thenAccept(v -> {
            System.out.println("thenAccept current thread: " + Thread.currentThread().getName());
        });

        System.out.println("main current thread: " + Thread.currentThread().getName());
        System.out.println(future.get());


    }

    public CompletableFuture<String> setAsyncValue(String key) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("current thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return key + "value";
        });
    }

}
