package com.zhy.web.interceptor.annotation;

import cn.hutool.core.util.StrUtil;
import com.zhy.web.filter.MyServletRequestWrapper;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/8/8 15:18
 */
@Order(2)
@WebFilter("/annotation/*")
public class AnnotationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest myServletRequestWrapper = new MyServletRequestWrapper(request);
        filterChain.doFilter(myServletRequestWrapper, response);
    }
}
