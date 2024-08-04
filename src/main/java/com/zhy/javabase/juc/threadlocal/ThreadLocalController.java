package com.zhy.javabase.juc.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouhongyin
 * @since 2022/11/13 10:36
 */
@Slf4j
@RestController
@RequestMapping("thread_local")
public class ThreadLocalController {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @GetMapping("test1")
    public String test1(String s) throws InterruptedException {

        log.info("{}:{}", Thread.currentThread().getName(), threadLocal.get());

        threadLocal.set(s);
        threadLocal.set("2");
        threadLocal.remove();

        log.info("{}:{}", Thread.currentThread().getName(), threadLocal.get());

        Thread.sleep(4000);
        return s;
    }

    @Test
    public void test2() {

        threadLocal.set(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.set(Thread.currentThread().getName());
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }).start();

        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

    }

}
