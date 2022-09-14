package com.zhy.java基础.collection.queue;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author zhouhongyin
 * @since 2022/9/14 22:51
 */
public class PriorityQueueTest {

    @Test
    public void priorityQueueTest() {

        Queue<Integer> heap = new PriorityQueue<>((x, y) -> -(Integer.compare(x, y)));

        Integer[] a = {5, 6, 2, 12, 7, 3, 1};

        heap.addAll(Arrays.asList(a));

        System.out.println(heap.peek());


    }

}
