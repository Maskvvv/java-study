package com.zhy.javabase.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GCTest {
    public static void main(String[] args) {
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(new Data());
        }
        try {
            // 程序sleep 10分钟
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Data {
    byte[] array1 = new byte[1024 * 1024]; //1m
}
