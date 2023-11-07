package com.zhy.java基础.juc.lock.semaphore;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

/**
 * <p> producer consumer problem </p>
 *
 * @author zhouhongyin
 * @since 2023/11/7 10:40
 */
public class ProductProblem {

    public static final Semaphore SEMAPHORE = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            System.out.println("try acquire");

            try {
                SEMAPHORE.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("acquire success");

        });
        thread.start();

        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();

        SEMAPHORE.release();

        thread.join();
        System.out.println("thread success");

    }



}
