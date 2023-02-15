package com.zhy.java基础.Thread.lock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *  CyclicBarrier 的使用
 *
 *  和 CountDownLatch 的区别：
 *
 *      CountDownLatch 主要用来解决一个线程等待多个线程的场景，可以类比旅游团团长要等待所有的游客到齐才能去下一个景点；
 *      而 CyclicBarrier 是一组线程之间互相等待，更像是几个驴友之间不离不弃。
 *
 *      除此之外 CountDownLatch 的计数器是不能循环利用的，也就是说一旦计数器减到 0，再有线程调用 await()，该线程会直接通过。
 *      但 CyclicBarrier 的计数器是可以循环利用的，而且具备自动重置的功能，一旦计数器减到 0 会自动重置到你设置的初始值。
 *
 *      除此之外，CyclicBarrier 还可以设置回调函数，可以说是功能丰富。
 *
 * @author zhouhongyin
 * @since 2023/2/15 22:54
 */
public class CyclicBarrierSample {
    public static void main(String[] args) throws InterruptedException {

        // 当 barrier 被调用 5 词 await() 时，就会执行回调中的方法
        CyclicBarrier barrier = new CyclicBarrier(5, () -> {
            System.out.println("Action...GO again!");
        });

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicWorker(barrier));
            t.start();
        }
    }

    static class CyclicWorker implements Runnable {
        private final CyclicBarrier barrier;

        public CyclicWorker(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("Executed!");
                barrier.await();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
