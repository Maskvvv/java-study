package com.zhy.java8.functional;

import org.junit.Test;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author zhouhongyin
 * @since 2022/7/4 22:54
 */
public class PredicateTest {

    @Test
    public void predicateTest1() {
        Predicate<Integer> predicate = i -> i > 0;

        System.out.println(predicate.test(10));
    }

    @Test
    public void predicateTest2() {
        IntPredicate predicate = i -> i > 0;

        System.out.println(predicate.test(10));
    }

}
