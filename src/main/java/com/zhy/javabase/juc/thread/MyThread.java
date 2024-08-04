package com.zhy.javabase.juc.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/19 16:58
 */
public class MyThread implements Runnable {
    private LinkedBlockingQueue taskQueue = new LinkedBlockingQueue();
    private AtomicBoolean running = new AtomicBoolean(true);

    public void submitTask(Object task) throws InterruptedException {
        taskQueue.put(task);
    }

    @Override
    public void run() {
        while (running.get()) {
            try {
                Object task = taskQueue.take(); // 如果没有任务，会使线程阻塞
                doSomething(task);
                if (Thread.currentThread().isInterrupted()) {
                    //线程被中断，跳出循环，线程停止
                    break;
                }
                //这是一个耗时很长的方法
                doSomething2(task);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        if (running.compareAndSet(true, false)) {
            System.out.println(Thread.currentThread() + " is stoped");
        }
    }

    private void doSomething(Object task) {
    }

    private void doSomething2(Object task) {
    }
}



