package com.zhy.javabase.juc.lock.semaphore;

import java.util.concurrent.Semaphore;

/**
 * <p> smoker problem </p>
 * <pre>
 *     假设一个系统有三个抽烟者进程和一个供应者进程。每个抽烟者不停地卷烟并抽掉它，但是要卷起并抽掉一支烟，
 *     抽烟者需要有三种材料:烟草、纸和胶水。三个抽烟者中，第一个拥有烟草、第二个拥有纸、第三个拥有胶水。
 *     供应者进程无限地提供三种材料，供应者每次将两种材料放桌子上，拥有剩下那种材料的抽烟者卷一根烟并抽掉它，
 *     并给供应者进程一个信号告诉完成了，供应者就会放另外两种材料再桌上，这个过程一直重复 (让三个抽烟者轮流地抽烟)
 * </pre>
 *
 * @author zhouhongyin
 * @since 2023/11/7 14:32
 */
public class SmokerProblem {

    public static final Semaphore empty = new Semaphore(1);
    public static final Semaphore offer1 = new Semaphore(0);
    public static final Semaphore offer2 = new Semaphore(0);
    public static final Semaphore offer3 = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new SmokerProblem.Smoker1()).start();
        new Thread(new SmokerProblem.Smoker2()).start();
        new Thread(new SmokerProblem.Smoker3()).start();


        Semaphore[] semaphores = {offer1, offer2, offer3};
        for (int i = 0; i < 10; i++) {
            empty.acquire();
            System.out.println("生产烟草材料！");
            semaphores[i % 3].release();
        }


        System.out.println("end");

    }


    public static class Smoker1 implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    offer1.acquire();
                    System.out.println("smoker1 smoke!");
                    empty.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static class Smoker2 implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    offer2.acquire();
                    System.out.println("smoker2 smoke!");
                    empty.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public static class Smoker3 implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    offer3.acquire();
                    System.out.println("smoker3 smoke!");
                    empty.release();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }



}
