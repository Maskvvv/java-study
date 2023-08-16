package com.zhy.常用java类.string;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author zhouhongyin
 * @since 2022/6/16 16:00
 */
public class StringScannerTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        String target = sc.nextLine();

        int res = 0;
        str = str.toLowerCase();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == target.charAt(0)) {
                res++;
            }

        }

        System.out.println(res);
        Set<String> strings = new HashSet<>();
    }
}
