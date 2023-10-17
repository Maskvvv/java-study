package com.zhy.常用java类.integer;

import org.junit.Test;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/10/17 14:51
 */
public class MathTest {

    @Test
    public void getRandom1() {
        int len = 10000;

        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        System.out.println(rs);
    }

}
