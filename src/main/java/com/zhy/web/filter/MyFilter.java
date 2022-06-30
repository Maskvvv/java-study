package com.zhy.web.filter;

import cn.hutool.core.util.StrUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
@WebFilter(value = "/filter/path2")
public class MyFilter implements Filter {
    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter:/filter/path2");

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String method = httpServletRequest.getMethod();
        String contentType = httpServletRequest.getContentType();
        if (StrUtil.isNotEmpty(contentType)) {
            contentType = contentType.toLowerCase();
        }

        // 该方法处理 POST请求并且contentType为application/json格式的
        if (HttpMethod.POST.name().equalsIgnoreCase(method) && StrUtil.isNotEmpty(contentType) && contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
            MyServletRequestWrapper myServletRequestWrapper = new MyServletRequestWrapper(httpServletRequest);
            //System.out.println(myServletRequestWrapper.getBody());
            httpServletRequest = myServletRequestWrapper;
        }

        chain.doFilter(httpServletRequest, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
