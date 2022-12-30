package com.zhy.常用java类.string;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2022/6/16 16:00
 */
public class StringTest4 {

    @Test
    public void subString() {
        String s = "{\"code\":4091108.0,\"success\":false,\"tracer\":\"a16686b5edc4dad0e8065e01635238ec\",\"message\":\"ææºå·ç å·²è¢«å ç¨\",\"status\":409.0}";

        byte[] bytes = s.getBytes(StandardCharsets.ISO_8859_1);


        String s1 = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(s1);

    }

}
