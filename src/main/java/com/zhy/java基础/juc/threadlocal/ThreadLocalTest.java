package com.zhy.java基础.juc.threadlocal;

import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/11/13 10:36
 */

public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Test
    public void test1() throws InterruptedException {

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("2");

        System.gc();

        Thread.sleep(5 * 1000);

        System.out.println(threadLocal.get());

    }

}
