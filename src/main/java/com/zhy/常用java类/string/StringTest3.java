package com.zhy.常用java类.string;

import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/6/16 16:00
 */
public class StringTest3 {
    public static void main(String[] args) {
        char a = '9';
        System.out.println(a);

        System.out.println((int) a);

        System.out.println(Integer.valueOf(a));

        String b = "aaabbbccc";
        boolean aaa = b.contains("aaaa");
        System.out.println(aaa);

        char c = 'Z';

        System.out.println((char) (Character.toLowerCase(c) + 1));


        System.out.println("a".matches("[0-9a-fA-F]"));

    }


    @Test
    public void subString() {
        String s = "aaa  bbbbcc";

        System.out.println(s.length());


        System.out.println(s.substring(0, 8));

    }
}
