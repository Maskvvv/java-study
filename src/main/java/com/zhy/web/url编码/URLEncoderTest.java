package com.zhy.web.url编码;

import com.zhy.web.utils.URLEncodeUtil;
import com.zhy.web.utils.URLEncoder;

import java.io.UnsupportedEncodingException;

/**
 * @author zhouhongyin
 * @since 2022/6/8 10:04
 */
public class URLEncoderTest {
    public static void main(String[] args) throws Exception {
        //System.out.println(URLEncoder.encode("有空格 you空格.doc"));
        //
        //System.out.println(URLEncoder.encode("有空格 you空格.doc","UTF-8"));
        //
        //System.out.println(URLEncoder.encode("有空格 you空格.doc","GBK"));
        //
        //String encode = URLEncoder.encode("有空格 you空格.doc", "UTF-8");
        //
        //System.out.println(URLEncodeUtil.encode("有空格 you空格.doc"));
        //
        //// 处理中文文件名的问题
        //String gbk = new String(encode.getBytes(StandardCharsets.UTF_8), "GBK");
        //System.out.println(gbk);

        String encode = URLEncodeUtil.encode("有空格 you空格.doc");
        System.out.println(encode);

        System.out.println(URLEncodeUtil.encodeAll("有空格 you空格.doc"));


    }
}
