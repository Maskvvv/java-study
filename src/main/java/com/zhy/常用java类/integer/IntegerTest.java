package com.zhy.常用java类.integer;

/**
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
    }
}
