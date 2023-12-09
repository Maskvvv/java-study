package com.zhy.java基础.juc.thread;

import org.junit.Test;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2023/12/9 10:30
 */
public class InterruptTest {


    @Test
    public void interruptTest1() throws IOException {
        Thread thread = new Thread(() -> {
            try {
                long i = 0;
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }
                    i++;
                    System.out.println(i);
                }
            } catch (Exception e) {
                System.out.println(e);
            }


        });


        thread.start();

        thread.interrupt();

        System.in.read();
    }
}
