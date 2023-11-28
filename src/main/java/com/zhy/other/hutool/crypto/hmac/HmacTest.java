package com.zhy.other.hutool.crypto.hmac;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.junit.jupiter.api.Test;

/**
 * <p> HMAC </p>
 * <p>
 * HMAC，全称为“Hash Message Authentication Code”，中文名“散列消息鉴别码”，主要是利用哈希算法，以一个密钥和一个消息为输入，
 * 生成一个消息摘要作为输出。一般的，消息鉴别码用于验证传输于两个共同享有一个密钥的单位之间的消息。HMAC 可以与任何迭代散列函数捆绑使用。
 * MD5 和 SHA-1 就是这种散列函数。HMAC 还可以使用一个用于计算和确认消息鉴别值的密钥。
 * </p>
 *
 * @author zhouhongyin
 * @since 2023/11/28 11:50
 */
public class HmacTest {


    @Test
    public void MD5() {
        String testStr = "test中文";

        // 此处密钥如果有非ASCII字符，考虑编码
        byte[] key = "password".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacMD5, key);

        // b977f4b13f93f549e06140971bded384
        String macHex1 = mac.digestHex(testStr);

    }

}
