package com.zhy.spring.aop;

import lombok.extern.slf4j.Slf4j;
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
 * @since 2022/8/22 17:14
 */
@Slf4j
@WebFilter(value = "/aop/**")
public class AopFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("-----------------Filter-begin-----------------");
        filterChain.doFilter(request, response);
        log.info("-----------------Filter-end-----------------");
    }
}
