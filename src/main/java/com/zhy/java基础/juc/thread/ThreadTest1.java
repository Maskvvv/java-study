package com.zhy.java基础.juc.thread;

/**
 * @author zhouhongyin
 * @since 2022/6/7 21:50
 */
public class ThreadTest1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("thread2 start");

        new Thread(() -> {
            System.out.println("thread1 start");

            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("thread1 end");

        }).start();


        Thread.sleep(1000L);
        System.out.println("thread2 end");
    }
}
