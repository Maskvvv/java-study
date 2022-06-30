package com.zhy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2022/6/24 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Test
    public void ThreadTest() {


        threadPoolTaskExecutor.execute(() -> {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName() + "->" + i);
            }
        });


        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "->" + i);
        }

        int[] ints = new int[1];

    }

}
