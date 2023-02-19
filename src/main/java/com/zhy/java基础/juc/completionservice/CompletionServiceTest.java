package com.zhy.java基础.juc.completionservice;

import org.junit.Test;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * CompletionService 的使用
 *
 * 创建：
 *      ExecutorCompletionService(Executor executor)；
 *      ExecutorCompletionService(Executor executor, BlockingQueue> completionQueue)。
 *
 * 使用：
 *
 *      Future submit(Callable task);
 *      Future submit(Runnable task, V result);
 *
 *      take()、poll() 都是从阻塞队列中获取并移除一个元素；它们的区别在于如果阻塞队列是空的，那么调用 take() 方法的线程会被阻塞，而 poll() 方法会返回 null 值
 *      Future take() throws InterruptedException;
 *      Future poll();
 *      Future poll(long timeout, TimeUnit unit) throws InterruptedException;
 *
 * @author zhouhongyin
 * @since 2023/2/19 17:35
 */
public class CompletionServiceTest {

    @Test
    public void test1() throws InterruptedException, ExecutionException {

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 异步向电商S1询价
        cs.submit(() -> 1);
        // 异步向电商S2询价
        cs.submit(() -> 2);
        // 异步向电商S3询价
        cs.submit(() -> 3);

        // 将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Future<Integer> take = cs.take();
            Integer r = take.get();
            executor.execute(() -> System.out.println(r));
        }
    }

}
