package com.zhy.other.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/26 9:19
 */
public class RateLimiterTest {

    @Test
    public void test1() throws InterruptedException {
        RateLimiter rateLimiter = RateLimiter.create(1);


        //Thread.sleep(400);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(500);
            if (!rateLimiter.tryAcquire()) {
                System.out.println("被限流");
            } else {
                System.out.println("做点什么");
            }
        }
    }

}
