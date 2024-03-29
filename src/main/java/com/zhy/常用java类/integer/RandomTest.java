package com.zhy.常用java类.integer;

import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * <p> RandomTest </p>
 *
 * @author zhouhongyin
 * @since 2023/10/17 14:54
 */
public class RandomTest {

    @Test
    public void test1() {

        int len = 5;

        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        System.out.println(rs.toString());

    }

    @Test
    public void test2() {

        int len = 2;

        Random r = new Random();
        for (int i = 0; i < len; i++) {
            System.out.println(r.nextInt(3));
        }

    }

}
