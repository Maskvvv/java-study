package com.zhy.java基础.juc.future.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture
 * @author zhouhongyin
 * @since 2023/2/16 22:55
 */
public class CompletableFutureTest {

    // TODO-zhouhy 2023/2/16 CompletableFuture 的使用
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "1");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "2");
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "3");
        CompletableFuture.anyOf(completableFuture1, completableFuture2, completableFuture3)
                .thenAccept(System.out::println);
    }
}
