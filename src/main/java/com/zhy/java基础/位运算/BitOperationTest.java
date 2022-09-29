package com.zhy.java基础.位运算;

import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/9/29 21:35
 */
public class BitOperationTest {

    @Test
    public void bitOperationTest1() {
        int a = 15;
        int b = 16;

        System.out.println(a % b);
        System.out.println(a & b);
        System.out.println(a | b);
        System.out.println(a ^ b);

    }

}
