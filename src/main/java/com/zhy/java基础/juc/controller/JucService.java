package com.zhy.java基础.juc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/29 16:49
 */
@Slf4j
@Service
public class JucService {


    public synchronized void sync() throws InterruptedException {

        log.info("thread: {}, start", Thread.currentThread().getName());

        Thread.sleep(10 * 1000);

    }

}
