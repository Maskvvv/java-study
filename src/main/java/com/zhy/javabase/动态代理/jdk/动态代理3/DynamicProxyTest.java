package com.zhy.javabase.动态代理.jdk.动态代理3;

import com.zhy.javabase.动态代理.modle.People;
import com.zhy.javabase.动态代理.modle.Student;
import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/12/15 15:59
 */
public class DynamicProxyTest {

    @Test
    public void test1() {
        JdkPeopleFactory jdkPeopleFactory = new JdkPeopleFactory(new Student());
        People people = ((People) jdkPeopleFactory.getPeople());
        people.say();
    }

}
