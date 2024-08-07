package com.zhy.javabase.位运算;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

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

        BitSet bitSet = new BitSet();
        bitSet.set(100000000);
        System.out.println(bitSet.get(100000000));
        System.out.println(bitSet.size());
        System.out.println(bitSet.length());
        System.out.println(Arrays.toString(bitSet.toLongArray()));

    }

    @Test
    public void bitOperationTest2() {
        long a = -2L;

        System.out.println(a & Long.MAX_VALUE);
        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

    }

    @Test
    public void bitOperationTest3() {
        long a = 1L << 1;
        long b = 1L << 2;
        long c = 4L >>> 1;

        System.out.println(c);
        System.out.println(a | b);
        System.out.println(a & b);

    }

    @Test
    public void bitOperationTest4() {
        long a = 1L << 1;
        long b = 0b0011;

        System.out.println(a ^ b);

    }

}
