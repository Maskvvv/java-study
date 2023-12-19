package com.zhy.常用java类.integer;

import org.junit.jupiter.api.Test;

/**
 * <p> Integer Test</p>
 *
 * @author zhouhongyin
 * @since 2022/6/20 20:56
 */
public class IntegerTest {

    public static void main(String[] args) {

        int a = Integer.parseInt("5", 16);
        System.out.println(a);

        String s = Integer.toBinaryString(a);
        System.out.println(s);

        int anInt = Integer.parseInt(s, 2);
        System.out.println(anInt);

        String s1 = Integer.toHexString(anInt);
        System.out.println(s1);

        Integer z = 1;
        Integer q = 2;

        Double cv = Double.valueOf(z) / q;

        System.out.println(cv);

    }

    /**
     * 数值转换溢出
     */
    @Test
    public void overflow() {
        // 1 1000 0000 0000 0000 0000 0000 0000 0000
        long l = 6442450944L;
        int i = (int) l;

        System.out.println(i);
    }

    @Test
    public void pow2() {
        System.out.println(Math.pow(2, 10));
        System.out.println(0x000003FF);

        double i = 1e3;
        System.out.println((int) i);
    }

    @Test
    public void compare() {
        Integer i1 = 1000;
        Integer i2 = 10000;

        System.out.println(i1.compareTo(i2));
    }
}
