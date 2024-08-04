package com.zhy.javabase.collection.queue;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 队列
 * @author zhouhongyin
 * @since 2022/4/29 10:12
 */
public class DequeDemo2 {
    @Test
    public void arrayQueue() {
        Deque<Integer> queue = new ArrayDeque<>();
        // 入队
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue);

        // 出队
        queue.poll();
        queue.remove();
        System.out.println(queue);

        // 获取队头元素
        System.out.println(queue.peek());
        System.out.println(queue.peekFirst());
    }

    @Test
    public void linkedQueue() {
        Deque<Integer> queue = new LinkedList<>();
        // 入队
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        System.out.println(queue);

        // 出队
        queue.poll();
        queue.remove();
        System.out.println(queue);

        // 获取队头元素
        System.out.println(queue.peek());
        System.out.println(queue.peekFirst());
    }


}
