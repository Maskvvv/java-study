package com.zhy.web.interceptor;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.web.filter.MyServletRequestWrapper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhouhongyin
 * @since 2022/6/29 17:15
 */
@Component
public class MyHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //String body = ((MyServletRequestWrapper) request).getBody();

        MyServletRequestWrapper requestWrapper = (MyServletRequestWrapper) request;

        //System.out.println(body);

        //JSONObject jsonObject = JSON.parseObject(body);
        //for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
        //    String key = entry.getKey();
        //    String value = (String) entry.getValue();
        //
        //    if (Base64.isBase64(value)) {
        //        jsonObject.put(key, Base64.decodeStr(value));
        //    }
        //}
        //
        //
        //byte[] bytes = jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8);

        byte[] decode = Base64.decode(requestWrapper.getBody());

        ((MyServletRequestWrapper) request).setBody(decode);

        //System.out.println(((MyServletRequestWrapper) request).getBody());

        return true;
    }
}
