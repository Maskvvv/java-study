package com.zhy.java基础.system;

import org.junit.Test;

/**
 * <p>读取系统环境变量和属性值</p>
 *
 * @author zhouhongyin
 * @since 2023/5/31 15:54
 */
public class SystemTest {

    @Test
    public void envAndProperties() {

        System.out.println(System.getenv());
        System.out.println(System.getProperties());
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));

    }

}
