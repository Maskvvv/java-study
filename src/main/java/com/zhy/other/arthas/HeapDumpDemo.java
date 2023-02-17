package com.zhy.other.arthas;


import com.zhy.other.arthas.model.HeapData;

import java.util.ArrayList;
import java.util.List;

/**
 * heap dump
 *
 * @author zhouhongyin
 * @since 2023/2/17 11:06
 */
public class HeapDumpDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(() -> {
            List<HeapData> list = new ArrayList<>();
            for (int i = 0; i < 200; i++) {
                list.add(new HeapData());
            }

            try {
                Thread.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setName("heap-dump-thread");
        thread.start();


        thread.join();
    }

}

