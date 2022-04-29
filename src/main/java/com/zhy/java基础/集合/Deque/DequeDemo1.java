package com.zhy.java基础.集合.Deque;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 栈
 * @author zhouhongyin
 * @since 2022/4/29 10:12
 */
public class DequeDemo1 {
    @Test
    public void arrayStack() {
        Deque<Integer> stack = new ArrayDeque<>();
        // 入栈
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);

        // 出栈
        stack.pop();
        stack.remove();
        System.out.println(stack);

        // 获取栈顶元素
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
    }

    @Test
    public void arrayStack1() {
        Deque<Integer> stack = new ArrayDeque<>();
        // 入栈
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        stack.addFirst(4);
        System.out.println(stack);

        // 出栈
        stack.removeFirst();
        System.out.println(stack);

        // 获取栈顶元素
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
    }

    @Test
    public void arrayStack2() {
        Deque<Integer> stack = new ArrayDeque<>();
        // 入栈
        stack.addLast(1);
        stack.addLast(2);
        stack.addLast(3);
        stack.addLast(4);
        System.out.println(stack);

        // 出栈
        stack.removeLast();
        System.out.println(stack);

        // 获取栈顶元素
        System.out.println(stack.peekLast());
    }

    @Test
    public void linkedStack() {
        Deque<Integer> stack = new LinkedList<>();
        // 入栈
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);

        // 出栈
        stack.pop();
        stack.remove();
        System.out.println(stack);

        // 获取栈顶元素
        System.out.println(stack.peek());
        System.out.println(stack.peekFirst());
    }

    @Test
    public void stack() {
        Stack<Integer> stack = new Stack<>();
        // 入栈
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack);

        // 出栈
        stack.pop();
        System.out.println(stack);

        // 获取栈顶元素
        System.out.println(stack.peek());
    }


}
