package com.zhy.java8.functional;

import org.junit.Test;

import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

/**
 * @author zhouhongyin
 * @since 2022/7/4 23:06
 */
public class FunctionTest {

    @Test
    public void functionTest1() {

        Function<String, String> function = target -> target + " hahaha";

        System.out.println(function.apply("ennenee"));

    }

    @Test
    public void functionTest2() {

        UnaryOperator<String> function = target -> target + " hahaha";

        System.out.println(function.apply("ennenee"));

    }

    @Test
    public void functionTest3() {

        IntUnaryOperator function = target -> target + 1;

        System.out.println(function.applyAsInt(2));

    }

    @Test
    public void functionTest4() {

        Function<Integer, Function<Integer, Function<Integer, Integer>>> function = x -> y -> z -> x + y + z;

        System.out.println(function.apply(1).apply(2).apply(3));

    }

}
