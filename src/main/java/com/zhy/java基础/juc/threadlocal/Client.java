package com.zhy.java基础.juc.threadlocal;

import org.junit.Test;

public class Client {


    @Test
    public void normalReference() {

        Salad salad = new Salad(new Apple("红富士"), new Object());
        //通过WeakReference的get()方法获取Apple
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            //休眠一下，在运行的时候加上虚拟机参数-XX:+PrintGCDetails，输出gc信息，确定gc发生了。
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("apple:" + salad.get());
        System.out.println("value:" + salad.getValue());
    }


    static Apple apple = new Apple("红富士");

    @Test
    public void staticReference() {


        Salad salad = new Salad(apple, new Object());
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("apple:" + salad.get());
        System.out.println("value:" + salad.getValue());
    }

    @Test
    public void clear() {


        Salad salad = new Salad(apple, new Object());
        System.out.println("Apple:" + salad.get());
        System.gc();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        salad.clear();

        System.out.println("apple:" + salad.get());
        System.out.println("value:" + salad.getValue());
    }


}
