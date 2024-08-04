package com.zhy.javabase.动态代理.cglib;

import com.zhy.javabase.动态代理.modle.People;
import com.zhy.javabase.动态代理.modle.Student;
import org.junit.Test;

/**
 * @author zhouhongyin
 * @since 2022/12/15 16:14
 */
public class CglibDynamicTest {

    @Test
    public void test1() {
        CglibPeopleFactory cglibPeopleFactory = new CglibPeopleFactory(new Student());
        People people = ((People) cglibPeopleFactory.getPeople());

        people.say();
        people.hashCode();
    }

}
