package com.zhy.other.hutool.crypto.digester;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
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


    @Test
    public void test4() throws IOException {
        String testStr = "nonce_str=Pi2Gi78LuWFLlxl2UCqf4fnyTbG6HrMjjb7P3lepVYW04exP6C9YqZZg7pYBM3ba&request_type=purchase&success=1&transaction[id]=xxxxxx&transaction[transaction_id]=1234567890123456&transaction[reference_id]=123123123123123123&transaction[custom_id]=6543210987654321&transaction[payment_method]=VISA&transaction[currency]=HKD&transaction[amount]=5.00&transaction[status]=paid&transaction[paid_at]=2018-07-12 16:00:43&transaction[refunded_at]=&transaction[updated_at]=2018-07-12 16:07:56&transaction[extra_parameters][customer_name]=Yed Pay&transaction[extra_parameters][phone]=59770850";

        byte[] key = "00112233445566778899aabbccddeeff".getBytes();
        HMac mac = new HMac(HmacAlgorithm.HmacSHA256, key);

        String macHex1 = mac.digestHex(testStr);
        System.out.println(macHex1);


        String s = "test1234";
        System.out.println(s.substring("".length()));
    }

}
