package com.zhy.java基础.reference;

import java.lang.ref.WeakReference;

/**
 * <p>  </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 13:49
 */
public class Salad extends WeakReference<Apple> {
    public Salad(Apple apple) {
        super(apple);
    }

    public static void main(String[] args) {
        Salad salad = new Salad(new Apple("红富士"));
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());

        //salad.refersTo(new Apple("红富士111"));
        //Apple apple = salad.get();

        salad.clear();

        System.out.println("clear-Apple:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //如果为空，代表被回收了
        if (salad.get() == null) {
            System.out.println("clear Apple。");
        }

    }
}

