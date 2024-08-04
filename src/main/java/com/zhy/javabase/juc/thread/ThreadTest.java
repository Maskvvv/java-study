package com.zhy.javabase.juc.thread;

/**
 *
 * @author zhouhongyin
 * @since 2021/8/1 12:04
 */

public class ThreadTest {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        myThread1.run();
        myThread2.run();

    }

}

class MyThread1 extends Thread {
    @Override
    public void run() {
        int count = 500;
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                System.out.println("1-" + i);
            }
        }
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        int count = 500;
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0) {
                System.out.println("2-" + i);
            }
        }
    }
}
