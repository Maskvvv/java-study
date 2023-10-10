package com.zhy.other.guava;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * <p> Guava Stopwatch </p>
 *
 * @author zhouhongyin
 * @since 2023/10/10 23:10
 */
public class TimeIntervalTest {
    public static void main(String[] args) throws InterruptedException {
        // 创建并启动计时器
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 执行时间（1s）
        Thread.sleep(1000);
        // 停止计时器
        stopwatch.stop();
        // 执行时间（单位：秒）
        System.out.printf("执行时长：%d 秒. %n", stopwatch.elapsed().getSeconds()); // %n 为换行
        // 执行时间（单位：毫秒）
        System.out.printf("执行时长：%d 豪秒.", stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }
}
