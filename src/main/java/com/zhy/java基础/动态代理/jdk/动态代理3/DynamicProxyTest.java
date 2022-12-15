package com.zhy.java基础.动态代理.jdk.动态代理3;

import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/12/15 15:59
 */
public class DynamicProxyTest {

    @Test
    public void test1() {
        PeopleFactory peopleFactory = new PeopleFactory(new Student());
        People people = ((People) peopleFactory.getPeople());
        people.say();
    }

}
