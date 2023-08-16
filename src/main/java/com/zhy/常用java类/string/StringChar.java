package com.zhy.常用java类.string;

import java.util.Scanner;

/**
 * @author zhouhongyin
 * @since 2022/6/16 16:00
 */
public class StringChar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();

        int j = 1;

        int res = 0;
        for (int i = str.length() - 1; i >= 2; i--) {
            int cha = str.charAt(i);

            if (cha >= 'A') {
                cha = cha - 'A' + 10;
            } else {
                cha = cha - '0';
            }
            res = res + (cha *  j);
            j = j * 16;
        }
    }
}
