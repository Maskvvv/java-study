package com.zhy.常用java类.string;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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
        String s = "123456789123王浩";

        System.out.println(s.length());


        System.out.println(s.substring(0, 8));

    }

    @Test
    public void split() {
        String s = "1,2,3,4,5,6";

        String[] split = s.split(",");

        List<String> l1 = Arrays.asList(split);

        //List<String> l2 = List.of(split);

        System.out.println(l1);
    }


}
