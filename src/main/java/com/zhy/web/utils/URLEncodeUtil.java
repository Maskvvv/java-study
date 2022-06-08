package com.zhy.web.utils;


import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2022/6/8 10:18
 */
public class URLEncodeUtil {

    /**
     * ISO-8859-1
     */
    public static final String ISO_8859_1 = "ISO-8859-1";
    /**
     * UTF-8
     */
    public static final String UTF_8 = "UTF-8";
    /**
     * GBK
     */
    public static final String GBK = "GBK";

    /**
     * ISO-8859-1
     */
    public static final Charset CHARSET_ISO_8859_1 = StandardCharsets.ISO_8859_1;
    /**
     * UTF-8
     */
    public static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;


    /**
     * 编码URL，默认使用UTF-8编码<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL自动编码，类似于浏览器中键入地址自动编码，对于像类似于“/”的字符不再编码
     *
     * @param url URL
     * @return 编码后的URL
     * @since 3.1.2
     */
    public static String encode(String url) throws UnsupportedEncodingException {
        return encode(url, CHARSET_UTF_8);
    }

    /**
     * 编码字符为 application/x-www-form-urlencoded<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL自动编码，类似于浏览器中键入地址自动编码，对于像类似于“/”的字符不再编码
     *
     * @param url     被编码内容
     * @param charset 编码
     * @return 编码后的字符
     * @since 4.4.1
     */
    public static String encode(String url, Charset charset) {
        return URLEncoder.DEFAULT.encode(url, charset);
    }

}
