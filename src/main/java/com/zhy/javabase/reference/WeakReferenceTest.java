package com.zhy.javabase.reference;

import java.lang.ref.WeakReference;

/**
 * <p> WeakReference </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 13:50
 */
public class WeakReferenceTest {
    public static void main(String[] args) throws InterruptedException {
        Apple apple = new Apple("1");
        WeakReference<Apple> weakReference1 = new WeakReference<>(apple);
        WeakReference<Apple> weakReference2 = new WeakReference<>(new Apple("2"));

        System.out.println(weakReference1.get());
        System.out.println(weakReference2.get());
        System.gc();

        Thread.sleep(3 * 1000);

        System.out.println(weakReference1.get());
        System.out.println(weakReference2.get());
    }

}
