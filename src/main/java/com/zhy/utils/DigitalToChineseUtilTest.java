package com.zhy.utils;

import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/9/30 9:53
 */
public class DigitalToChineseUtilTest {

    @Test
    public void digitalToChineseUtilTest() {
        String s = DigitalToChineseUtil.int2chineseNum(123);
        System.out.println(s);
    }

}
