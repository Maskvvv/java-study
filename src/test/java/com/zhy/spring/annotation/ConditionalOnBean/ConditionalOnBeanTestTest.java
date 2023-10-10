package com.zhy.spring.annotation.ConditionalOnBean;

import com.zhy.JavaStudyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhouhongyin
 * @since 2022/7/4 14:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class ConditionalOnBeanTestTest{

    @Autowired
    private People people1;

    @Test
    public void testPeople1() {
        System.out.println(people1);
    }
}
