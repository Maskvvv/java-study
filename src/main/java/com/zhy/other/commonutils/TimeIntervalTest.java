package com.zhy.other.commonutils;

import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * <p> commons-lang3 StopWatch </p>
 *
 * @author zhouhongyin
 * @since 2023/10/10 23:11
 */
public class TimeIntervalTest {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        // 开始时间
        stopWatch.start();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 结束时间
        stopWatch.stop();
        // 统计执行时间（秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.SECONDS) + " 秒.");
        // 统计执行时间（毫秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.MILLISECONDS) + " 毫秒.");
        // 统计执行时间（纳秒）
        System.out.println("执行时长：" + stopWatch.getTime(TimeUnit.NANOSECONDS) + " 纳秒.");
    }
}
