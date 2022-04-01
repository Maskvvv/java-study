package com.zhy.threadtest;

import com.zhy.JavaStudyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * @author zhouhongyin
 * @since 2021/8/3 16:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class ThreadPoolTaskExecutorTest {

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

    @Test
    public void parallelStreamTest() {
        List<Integer> integerList = new ArrayList<>();

        for (int i = 0; i < 20000; i++) {
            integerList.add(i);
        }

        integerList.parallelStream().forEach(integer -> {
            System.out.println(Thread.currentThread().getName() + ">>" + integer);
        });

        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + ">>" + "c" + i);
        }

    }

    @Test
    public void parallelStreamTest2() {

        long startTime = System.currentTimeMillis();

        List<Map<String, Integer>> mapsL = new ArrayList<>();

        for (int i = 0; i < 500000; i++) {
            Map<String, Integer> map1 = new HashMap<>();
            map1.put("key", i);
            mapsL.add(map1);
        }

        //List<Map<String, Integer>> maps2 = new Vector<>();
        List<Map<String, Integer>> maps2 = new ArrayList<>();

        mapsL.parallelStream().filter(iter -> {

            //System.out.println(Thread.currentThread().getName()+">>"+iter.get("key"));

            return iter.get("key") < 50000;
        }).forEach(maps2::add);//4

        if (maps2.size() < 50000) {//5
            System.out.println("forEach Error !" + maps2.size());
        }

        System.out.println("消耗时间：" + (System.currentTimeMillis() - startTime));
    }

    @Test
    public void annotationThreadTest1() {

        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "->" + i);
        }

        //threadPoolTaskExecutor.execute(() -> annotationThreadTest2());

        //annotationThreadTest2();

        new Thread(this::annotationThreadTest2).start();


        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
        System.out.println(Thread.currentThread().getName() + "parallelStream下方↓");
    }

    @Async
    public void annotationThreadTest2() {

        System.out.println("@Async");
        System.out.println("@Async");
        System.out.println("@Async");
        System.out.println("@Async");
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "->" + i);
        }

        System.out.println("@Async");
        System.out.println("@Async");
        System.out.println("@Async");
        System.out.println("@Async");

    }

    @Test
    public void mapSetTest() {
        Set<Map<String, String>> mapArrayList = new HashSet<>();

        Map<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("zhy1", "123");
        hashMap1.put("zhy2", "123");
        System.out.println(hashMap1);
        mapArrayList.add(hashMap1);

        System.out.println(mapArrayList);

        Map<String, String> hashMap2 = new HashMap<>();
        hashMap2.put("zhy1", "123");
        hashMap2.put("zhy2", "123");
        mapArrayList.add(hashMap2);

        System.out.println(mapArrayList);

    }

}
