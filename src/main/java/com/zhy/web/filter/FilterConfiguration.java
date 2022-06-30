package com.zhy.web.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2022/6/30 11:21
 */
@Configuration
public class FilterConfiguration {

    //@Bean
    //public FilterRegistrationBean<MyFilter> myFilterFilterRegistrationBean(MyFilter myFilter) {
    //    FilterRegistrationBean<MyFilter> myFilterRegistrationBean = new FilterRegistrationBean<>();
    //    myFilterRegistrationBean.setFilter(myFilter);
    //    myFilterRegistrationBean.setOrder(1);
    //    myFilterRegistrationBean.addUrlPatterns("/filter/path2");
    //
    //    return myFilterRegistrationBean;
    //}


    @Bean
    public FilterRegistrationBean<MyOncePerRequestFilter> myFilterFilterRegistrationBean(MyOncePerRequestFilter myOncePerRequestFilter) {
        FilterRegistrationBean<MyOncePerRequestFilter> myFilterRegistrationBean = new FilterRegistrationBean<>();
        myFilterRegistrationBean.setFilter(myOncePerRequestFilter);
        myFilterRegistrationBean.setOrder(1);
        myFilterRegistrationBean.addUrlPatterns("/filter/path1");

        return myFilterRegistrationBean;
    }

}
