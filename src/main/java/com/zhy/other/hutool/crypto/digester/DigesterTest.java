package com.zhy.other.hutool.crypto.digester;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author zhouhongyin
 * @since 2022/6/27 21:23
 */
public class DigesterTest {

    public static void main(String[] args) {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        // 5393554e94bf0eb6436f240a4fd71282
        System.out.println(md5.digestHex("123"));

        md5.setSalt("222".getBytes());
        System.out.println(md5.digestHex("123"));

        System.out.println(md5.digestHex("222123"));



    }
}
