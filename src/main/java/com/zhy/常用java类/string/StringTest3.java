package com.zhy.常用java类.string;

import java.util.Scanner;

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
}
