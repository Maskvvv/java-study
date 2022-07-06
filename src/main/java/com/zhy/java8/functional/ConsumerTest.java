package com.zhy.java8.functional;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author zhouhongyin
 * @since 2022/7/4 22:59
 */
public class ConsumerTest {

    @Test
    public void consumerTest1() {
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(10);

    }

}
