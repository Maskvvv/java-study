package com.zhy.other.hutool.crypto.digester;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/6/27 21:23
 */
public class DigesterTest {

    /**
     * MD5
     */
    @Test
    public void test1() {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        // 5393554e94bf0eb6436f240a4fd71282
        System.out.println(md5.digestHex("123"));

        md5.setSalt("222".getBytes());
        System.out.println(md5.digestHex("123"));

        System.out.println(md5.digestHex("222123"));

    }

    /**
     * Base64
     */
    @Test
    public void test2() throws IOException {
        String header = Base64.encode("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");
        System.out.println(header);

        String payload = Base64.encode("{\"sub\":\"1234567890\",\"name\":\"John Doe\",\"iat\":1516239022}");
        System.out.println(payload);


    }

    /**
     * sha256
     */
    @Test
    public void test3() throws IOException {
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);
        sha256.setSalt("123".getBytes());
        String signature = sha256.digestHex("asjdflasjdlfjasldf");
        System.out.println(signature);


        String fileHash = sha256.digestHex(new File("D:\\UserFiles\\桌面\\团队参赛） (1).xlsx"));
        System.out.println(fileHash);
    }

}
