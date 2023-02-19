package com.zhy.java基础.juc.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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

    @Test
    public void test1() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1完成！");
            return "future1完成！";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2完成！");
            return "future2完成！";
        });

        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);

        try {
            combindFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
