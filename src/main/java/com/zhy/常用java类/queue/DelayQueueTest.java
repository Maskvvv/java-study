package com.zhy.常用java类.queue;

import lombok.Data;
import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhongyin
 * @since 2022/10/10 15:33
 */
public class DelayQueueTest {

    @Test
    public void delayQueueTest() throws InterruptedException {

        DelayQueue<MyDelayed> delayQueue = new DelayQueue<>();

        long delay = 3000;

        MyDelayed myDelayed = new MyDelayed(delay);
        //delayQueue.offer(myDelayed);

        new Thread(() -> {
            try {
                test1(delayQueue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        System.out.println("----------------------------");

        System.out.println(myDelayed.getDelay(TimeUnit.MILLISECONDS));

        System.out.println(delayQueue.take());

        //for (int i = 0; i < 4; i++) {
        //    System.out.println(System.currentTimeMillis());
        //    System.out.println(delayQueue.take());
        //
        //    Thread.sleep(1000);
        //
        //}

    }

    public static void test1(DelayQueue<MyDelayed> delayQueue) throws InterruptedException {

        Thread.sleep(5000);
        long delay = 3000;

        MyDelayed myDelayed = new MyDelayed(delay);
        delayQueue.offer(myDelayed);

    }

}

@Data
class MyDelayed implements Delayed {

    private Long delayTime;

    public MyDelayed(Long delayTime) {
        this.delayTime = System.currentTimeMillis() + delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        System.out.println("getDelay" + (unit.convert(delayTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS)));
        //return unit.convert(delayTime, TimeUnit.MILLISECONDS) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return delayTime - System.currentTimeMillis();
    }


    @Override
    public int compareTo(Delayed o) {

        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        //return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
