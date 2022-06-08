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
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。
     *
     * @param url URL
     * @return 编码后的URL
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String encodeAll(String url) throws UnsupportedEncodingException {
        return encodeAll(url, CHARSET_UTF_8);
    }

    /**
     * 编码URL<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。
     *
     * @param url     URL
     * @param charset 编码，为null表示不编码
     * @return 编码后的URL
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     */
    public static String encodeAll(String url, Charset charset) throws UnsupportedEncodingException {
        return URLEncoder.ALL.encode(url, charset);
    }

    /**
     * 编码URL，默认使用UTF-8编码<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL自动编码，类似于浏览器中键入地址自动编码，对于像类似于“/”的字符不再编码
     *
     * @param url URL
     * @return 编码后的URL
     * @throws UnsupportedEncodingException UnsupportedEncodingException
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

    /**
     * 编码URL，默认使用UTF-8编码<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于POST请求中的请求体自动编码，转义大部分特殊字符
     *
     * @param url URL
     * @return 编码后的URL
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     * @since 3.1.2
     */
    public static String encodeQuery(String url) throws UnsupportedEncodingException {
        return encodeQuery(url, CHARSET_UTF_8);
    }

    /**
     * 编码字符为URL中查询语句<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于POST请求中的请求体自动编码，转义大部分特殊字符
     *
     * @param url     被编码内容
     * @param charset 编码
     * @return 编码后的字符
     * @since 4.4.1
     */
    public static String encodeQuery(String url, Charset charset) {
        return URLEncoder.QUERY.encode(url, charset);
    }

    /**
     * 编码URL，默认使用UTF-8编码<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL的Segment中自动编码，转义大部分特殊字符
     *
     * <pre>
     * pchar = unreserved（不处理） / pct-encoded / sub-delims（子分隔符） / "@"
     * unreserved = ALPHA / DIGIT / "-" / "." / "_" / "~"
     * sub-delims = "!" / "$" / "&amp;" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "="
     * </pre>
     *
     * @param url URL
     * @return 编码后的URL
     * @throws UnsupportedEncodingException UnsupportedEncodingException
     * @since 5.6.5
     */
    public static String encodePathSegment(String url) throws UnsupportedEncodingException {
        return encodePathSegment(url, CHARSET_UTF_8);
    }

    /**
     * 编码字符为URL中查询语句<br>
     * 将需要转换的内容（ASCII码形式之外的内容），用十六进制表示法转换出来，并在之前加上%开头。<br>
     * 此方法用于URL的Segment中自动编码，转义大部分特殊字符
     *
     * <pre>
     * pchar = unreserved / pct-encoded / sub-delims / ":" / "@"
     * unreserved = ALPHA / DIGIT / "-" / "." / "_" / "~"
     * sub-delims = "!" / "$" / "&amp;" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "="
     * </pre>
     *
     * @param url     被编码内容
     * @param charset 编码
     * @return 编码后的字符
     * @since 5.6.5
     */
    public static String encodePathSegment(String url, Charset charset) {
        if (url == null || url.length() == 0) {
            return url;
        }
        if (null == charset) {
            charset = CHARSET_UTF_8;
        }
        return URLEncoder.PATH_SEGMENT.encode(url, charset);
    }

    /**
     * 编码URL，默认使用UTF-8编码<br>
     * URL的Fragment URLEncoder<br>
     * 默认的编码器针对Fragment，定义如下：
     *
     * <pre>
     * fragment    = *( pchar / "/" / "?" )
     * pchar       = unreserved / pct-encoded / sub-delims / ":" / "@"
     * unreserved  = ALPHA / DIGIT / "-" / "." / "_" / "~"
     * sub-delims  = "!" / "$" / "&amp;" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "="
     * </pre>
     *
     * 具体见：https://datatracker.ietf.org/doc/html/rfc3986#section-3.5
     *
     * @param url     被编码内容
     * @return 编码后的字符
     * @since 5.7.13
     */
    public static String encodeFragment(String url) throws UnsupportedEncodingException {
        return encodeFragment(url, CHARSET_UTF_8);
    }

    /**
     * URL的Fragment URLEncoder<br>
     * 默认的编码器针对Fragment，定义如下：
     *
     * <pre>
     * fragment    = *( pchar / "/" / "?" )
     * pchar       = unreserved / pct-encoded / sub-delims / ":" / "@"
     * unreserved  = ALPHA / DIGIT / "-" / "." / "_" / "~"
     * sub-delims  = "!" / "$" / "&amp;" / "'" / "(" / ")" / "*" / "+" / "," / ";" / "="
     * </pre>
     *
     * 具体见：https://datatracker.ietf.org/doc/html/rfc3986#section-3.5
     *
     * @param url     被编码内容
     * @param charset 编码
     * @return 编码后的字符
     * @since 5.7.13
     */
    public static String encodeFragment(String url, Charset charset) {
        if (url == null || url.length() == 0) {
            return url;
        }
        if (null == charset) {
            charset = CHARSET_UTF_8;
        }
        return URLEncoder.FRAGMENT.encode(url, charset);
    }

}
