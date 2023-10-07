package com.zhy.常用java类.string.rgex;

import org.junit.jupiter.api.Test;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/10/7 16:06
 */
public class RgexTest {

    @Test
    public void test1() {

        String s = "12345678";
        System.out.println(s.replaceAll("(\\d)(?=\\d{3})+", "\\1,"));

        System.out.println(s.matches("(\\d)(?=\\d{3})+"));


    }

}
