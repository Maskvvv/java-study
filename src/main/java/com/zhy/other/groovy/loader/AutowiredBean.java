package com.zhy.other.groovy.loader;

import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2023/10/25 23:05
 */

@Component
public class AutowiredBean {

    public void print() {
        System.out.println("I am AutowiredBean");
    }

}
