package com.zhy.spring.utils;

import org.springframework.util.AntPathMatcher;

/**
 * @author zhouhongyin
 * @since 2024/10/10 15:06
 */
public class AntMatchTest {

    public static void main(String[] args) {


        SpringAntMatcher springAntMatcher = new SpringAntMatcher("/**/v2");

        System.out.println(springAntMatcher.matches("/op/v2"));


        AntPathMatcher antMatcher = new AntPathMatcher();
    }


    private static class SpringAntMatcher {
        private static final AntPathMatcher antMatcher = new AntPathMatcher();

        private final String pattern;

        private SpringAntMatcher(String pattern) {
            this.pattern = pattern;
        }

        public boolean matches(String path) {
            return antMatcher.match(pattern, path);
        }
    }

}
