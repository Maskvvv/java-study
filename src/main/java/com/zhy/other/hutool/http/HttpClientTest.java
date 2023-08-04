package com.zhy.other.hutool.http;

import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.io.IOException;

/**
 * <p> hutool http client</p>
 *
 * @author zhouhongyin
 * @since 2022/7/5 11:31
 */
public class HttpClientTest {


    @Test
    public void httpServerTest2() throws IOException {
        System.out.println(HttpUtil.get("http://aaaaa.top:8080/inet/ip"));
    }

}
