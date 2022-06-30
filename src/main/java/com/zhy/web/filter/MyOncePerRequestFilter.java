package com.zhy.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2022/6/29 14:30
 */
@WebFilter(value = "/filter/path1")
//@Component
public class MyOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("MyOncePerRequestFilter:filter/path1");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
