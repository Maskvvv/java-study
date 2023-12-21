package com.zhy.常用java类.string;

/**
 * <p> string equal </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 13:57
 */
public class SpringEqual {

    public static void main(String[] args) {

        String s1 = "1";
        String s2 = "1";
        System.out.println(s1 == s2);

        String s3 = new String("1");
        System.out.println(s3 == s1);

        String s4 = new String("1").intern();
        System.out.println(s4 == s1);
        System.out.println(s4 == s2);
    }

}
