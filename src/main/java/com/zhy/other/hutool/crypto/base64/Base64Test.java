package com.zhy.other.hutool.crypto.base64;

import cn.hutool.core.codec.Base64;
import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/6/30 9:22
 */
public class Base64Test {

    @Test
    public void base64Test1() {
        boolean isBase64 = Base64.isBase64("56m25a655L2T5LmI");
        System.out.println(isBase64);


        isBase64 = Base64.isBase64("66666");
        System.out.println(isBase64);


    }

}
