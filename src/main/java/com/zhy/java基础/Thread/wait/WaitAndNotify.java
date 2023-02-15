package com.zhy.java基础.Thread.wait;

/**
 * wait 和 notify 的使用
 *
 * @author zhouhongyin
 * @since 2023/2/14 16:01
 */
public class WaitAndNotify {
    public static void main(String[] args) {

        Object o = new Object();

        new Thread(() -> {
            try {
                synchronized (o) {
                    o.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("111111111111111111");


        new Thread(() -> {
            synchronized (o) {
                o.notifyAll();
            }
        }).start();


    }
}
