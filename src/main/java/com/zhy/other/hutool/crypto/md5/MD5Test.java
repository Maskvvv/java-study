package com.zhy.other.hutool.crypto.md5;

import cn.hutool.crypto.digest.MD5;
import org.junit.Test;

import java.io.File;

/**
 * @author zhouhongyin
 * @since 2022/9/9 10:03
 */
public class MD5Test {

    @Test
    public void md5() {
        String md5 = MD5.create().digestHex16(new File("D:\\UserFiles\\桌面\\学习\\极客时间\\计算机基础\\数据结构与算法之美\\22_22_21 _ 哈希算法（上）：如何防止数据库中的用户信息被脱库？.mhtml"));
        System.out.println(md5);

        String md51 = MD5.create().digestHex("lkhsdlfghslkg");
        System.out.println(md51);

    }
}
