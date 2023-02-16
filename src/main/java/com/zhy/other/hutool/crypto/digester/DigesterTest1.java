package com.zhy.other.hutool.crypto.digester;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/6/27 21:23
 */
public class DigesterTest1 {

    public static void main(String[] args) throws IOException {
        Digester sha256 = new Digester(DigestAlgorithm.SHA256);

        String header = Base64.encode("{\"alg\":\"HS256\",\"typ\":\"JWT\"}");
        System.out.println(header);

        String payload = Base64.encode("{\"sub\":\"1234567890\",\"name\":\"John Doe\",\"iat\":1516239022}");
        System.out.println(payload);


        sha256.setSalt("123".getBytes());
        String signature = sha256.digestHex(header + "." + payload);
        System.out.println(signature);

        System.out.println(header + "." + payload + "." + signature);




    }
}
