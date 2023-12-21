package com.zhy.常用java类.string;

import org.junit.Test;

import java.net.URLEncoder;
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

    @Test
    public void urlEncoder() {
        String s = "{0}你好，你预约的\"{1}\"即将开始，请安排好时间准时参加。\n" + "开始时间：{2}\n" + "地址：青岛海信财智谷1号楼青岛海信财智谷1号楼青岛海信财智谷1号楼青岛海信财智谷1号楼青岛";

        System.out.println(URLEncoder.encode(s));
        System.out.println(URLEncoder.encode(s).length());
        System.out.println(s.length());

    }

    @Test
    public void join() {
        String s1 = null;
        String s2 = "111";
        String s3 = null;

        System.out.println(String.join(":", s1, s2, s3));
    }
}
