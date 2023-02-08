package com.zhy.java基础.jvm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 从数据库中读取信用数据，套用模型，并把结果进行记录和传输
 *
 * 启动时加入以下参数 ： -Xms200M -Xmx200M -XX:+UseParallelGC  -XX:+PrintGC  -XX:+HeapDumpOnOutOfMemoryError
 * 发现启动后会频繁GC，最后导致OOM（OutOfMemoryError）
 */

public class T15_FullGC_Problem01 {

    private static class CardInfo {
        BigDecimal price = new BigDecimal(0.0);
        String name = "张三";
        int age = 5;

        Date birthdate = new Date();

        public void m() {}
    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * main方法
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        executor.setMaximumPoolSize(50);
        // 为什么是死循环？因为在生产环境中会有源源不断的数据需要处理，我们无法模拟线上环境， 所以用死循环代替；
        //for (int i = 0; i < 10000; i++){
        for (;;){
            modelFit();
            //correctModelFit();
            Thread.sleep(500);
        }

        //System.in.read();
    }

    /**
     * OOM 原因分析：
     * 引用关系：queue -> runnable -> cardInfo
     *
     */
    private static void modelFit(){
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            // do something
            executor.scheduleWithFixedDelay(() -> {
                //do sth with info
                info.m();

            }, 2, 3, TimeUnit.SECONDS);
        });
    }

    /**
     * 引用关系：queue -> runnable -/-> cardInfo
     */
    private static void correctModelFit(){
        executor.scheduleWithFixedDelay(() -> {
            List<CardInfo> taskList = getAllCardInfo();
            taskList.forEach(info -> {
                //do sth with info
                info.m();

            });
        }, 2, 3, TimeUnit.SECONDS);
    }

    private static List<CardInfo> getAllCardInfo(){
        List<CardInfo> taskList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            CardInfo ci = new CardInfo();
            taskList.add(ci);
        }

        return taskList;
    }
}
