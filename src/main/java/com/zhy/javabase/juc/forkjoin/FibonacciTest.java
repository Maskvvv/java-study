package com.zhy.javabase.juc.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Fork&Join 的使用
 *
 * ForkJoinTask 是一个抽象类，它的方法有很多，最核心的是 fork() 方法和 join() 方法，
 * 其中 fork() 方法会异步地执行一个子任务，而 join() 方法则会阻塞当前线程来等待子任务的执行结果。
 *
 * ForkJoinTask 有两个子类——RecursiveAction 和 RecursiveTask，通过名字你就应该能知道，
 * 它们都是用递归的方式来处理分治任务的。这两个子类都定义了抽象方法 compute()，
 * 不过区别是 RecursiveAction 定义的 compute() 没有返回值，
 * 而 RecursiveTask 定义的 compute() 方法是有返回值的。
 * 这两个子类也是抽象类，在使用的时候，需要你定义子类去扩展。
 *
 * @author zhouhongyin
 * @since 2023/2/19 18:45
 */
public class FibonacciTest {


    public static void main(String[] args) {
        //创建分治任务线程池
        ForkJoinPool fjp = new ForkJoinPool(4);
        //创建分治任务
        Fibonacci fib = new Fibonacci(2);
        //启动分治任务
        Integer result = fjp.invoke(fib);
        //输出结果
        System.out.println(result);
    }

    //递归任务
    static class Fibonacci extends RecursiveTask<Integer> {
        final int n;

        Fibonacci(int n) {
            this.n = n;
        }

        protected Integer compute() {
            if (n <= 1) return n;
            Fibonacci f1 = new Fibonacci(n - 1);
            //创建子任务
            f1.fork();
            Fibonacci f2 = new Fibonacci(n - 2);

            // f2 用当前线程执行，f1因为创建了子任务，所以等待结果就行
            //等待子任务结果，并合并结果
            return f2.compute() + f1.join();
        }
    }
}
