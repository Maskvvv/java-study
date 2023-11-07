package com.zhy.java基础.juc.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/11/4 22:12
 */
public class ThreadTest2 {

    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            list.add(new Thread(() -> {

                System.out.println("Thread " + count++ + " start");
                while (true) {
                    count++;

                }
            }));

        }


        for (Thread thread : list) {
            thread.start();
        }


        for (Thread thread : list) {
            thread.join();
        }
    }

}
