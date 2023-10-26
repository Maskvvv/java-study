package com.zhy.常用java类.string;

import java.util.Arrays;

/**
 * <p> StringIndexOfTest </p>
 *
 * @author zhouhongyin
 * @since 2022/6/16 16:00
 */
public class StringIndexOfTest {
    public static void main(String[] args) {
        String filename = "周志明个人简历.docx";

        System.out.println(Arrays.toString(filename.split("\\.")));

        String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
        System.out.println(extension);


        String filename2 = filename.substring(0, filename.lastIndexOf(".")).toLowerCase();
        System.out.println(filename2);


        String phoneNumber = "17662432931";
        System.out.println(phoneNumber.substring(phoneNumber.length() - 4));

    }
}
