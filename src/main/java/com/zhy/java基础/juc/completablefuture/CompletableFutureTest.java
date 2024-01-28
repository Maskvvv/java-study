package com.zhy.java基础.juc.completablefuture;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * CompletableFuture
 *
 * @author zhouhongyin
 * @since 2023/2/16 22:55
 */
public class CompletableFutureTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(10, new DefaultThreadFactory("completableFuture"));

    @Test
    public void runAsync() throws Exception {
        List<CompletableFuture<Void>> completableFutures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            completableFutures.add(
                    CompletableFuture.runAsync(() -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(Thread.currentThread().getName() + " is run!");
                    }, executorService)
            );
        }
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]));
        voidCompletableFuture.get(); // 阻塞

        System.out.println("all of run");
    }

    @Test
    public void anyOf() {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "1");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> "2");
        CompletableFuture<String> completableFuture3 = CompletableFuture.supplyAsync(() -> "3");
        CompletableFuture.anyOf(completableFuture1, completableFuture2, completableFuture3)
                .thenAccept(System.out::println);


    }

    @Test
    public void allOf() {
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

    /**
     * <img src="http://qiniu.zhouhongyin.top/2023/08/10/1691675692-20230810215451.png"/>
     * <p>
     * <p/>
     * 任务一：执行任务 TASK-A1，然后并发执行任务 TASK-B1、TASK-C1，再异步执行 TASK-D1；
     * <p/>
     * 任务二：执行任务 TASK-A2，然后并发执行任务 TASK-B2、TASK-C2，但是 TASK-B2、TASK-C2 中任意一个完成后，再异步执行 TASK-D2；
     */
    @Test
    public void testCombine() {
        ///////////////////////////////////////////////////
        // 任务一执行流程
        ///////////////////////////////////////////////////
        // 执行 taskA1
        CompletableFuture<Integer> taskA1 = CompletableFuture.supplyAsync(() -> 1);
        // taskA1 执行完后，再并发执行 taskB1、taskC1
        CompletableFuture<Integer> taskB1 = taskA1.thenApplyAsync(integer -> 2);
        CompletableFuture<Integer> taskC1 = taskA1.thenApplyAsync(integer -> 3);

        // 任务一的结果
        CompletableFuture<Integer> result1 =
                // 等到 taskB2、taskC2 都执行完并合并结果后
                taskB1.thenCombine(taskC1, Integer::sum)
                        // 再合并 taskA1 的结果后
                        .thenCombine(taskA1, Integer::sum)
                        // 再异步执行 taskD1
                        .thenApplyAsync(integer -> integer + 4);
        ///////////////////////////////////////////////////
        // 任务二执行流程
        ///////////////////////////////////////////////////
        // 执行 taskA2
        CompletableFuture<Integer> taskA2 = CompletableFuture.supplyAsync(() -> 1);
        // taskA2 执行完后，再并发执行 taskB2、taskC2
        CompletableFuture<Integer> taskB2 = taskA2.thenApplyAsync(integer -> 2);
        CompletableFuture<Integer> taskC2 = taskA2.thenApplyAsync(integer -> 3);
        // 任务二的结果
        CompletableFuture<Integer> result2 =
                // 等到 taskB2、taskC2 任意其中一个有结果后
                taskB2.applyToEither(taskC2, Function.identity())
                        // 再合并 taskA2 的结果后
                        .thenCombine(taskA2, Integer::sum)
                        // 再异步执行 taskD2
                        .thenApplyAsync(integer -> integer + 4);
        ///////////////////////////////////////////////////
        // 任务一 + 任务二，合并结果
        ///////////////////////////////////////////////////
        CompletableFuture<Integer> result = result1.thenCombine(result2, Integer::sum);
        try {
            // 任务总超时时间设置为5s
            System.out.println(result.get(5, TimeUnit.SECONDS));
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            // 超时则打印0
            System.out.println(0);
            e.printStackTrace();
        }

    }
}
