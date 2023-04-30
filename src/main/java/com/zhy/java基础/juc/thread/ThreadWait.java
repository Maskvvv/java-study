package com.zhy.java基础.juc.thread;

import com.zhy.model.git.entity.People;

/**
 * @author zhouhongyin
 * @since 2022/6/7 21:50
 */
public class ThreadWait {

    private final static People lock = new People();

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println("thread1 start");

            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread1 lock");

            }

            System.out.println("thread1 end");

        }).start();

        new Thread(() -> {
            System.out.println("thread2 start");

            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread2 lock");

            }

            System.out.println("thread2 end");

        }).start();

        new Thread(() -> {
            System.out.println("thread3 start");

            synchronized (lock) {

                lock.notifyAll();

                System.out.println("thread3 lock");

            }

            System.out.println("thread3 end");

        }).start();


        Thread.sleep(30000L);
    }
}
