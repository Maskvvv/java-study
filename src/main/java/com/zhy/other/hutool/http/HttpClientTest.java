package com.zhy.other.hutool.http;

import cn.hutool.http.HtmlUtil;
import cn.hutool.http.HttpRequest;
import org.junit.Test;

import java.io.IOException;

/**
 * <p> hutool http client </p>
 *
 * @author zhouhongyin
 * @since 2022/7/5 11:31
 */
public class HttpClientTest {


    @Test
    public void httpServerTest2() throws IOException {
        //System.out.println(HttpUtil.get("http://aaaaa.top:8080/inet/ip"));
        String str = HttpRequest.get("https://juejin.cn/post/6844903845227659271")
                .setHttpProxy("127.0.0.1", 10809)
                .execute().body();
        String result = HtmlUtil.cleanHtmlTag(str);
        System.out.println(result);
    }

}
