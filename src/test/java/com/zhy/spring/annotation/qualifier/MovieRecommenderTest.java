package com.zhy.spring.annotation.qualifier;

import com.zhy.JavaStudyApplication;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2023/1/9 22:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class MovieRecommenderTest extends TestCase {


    @Resource
    private MovieRecommender movieRecommender;

    @Test
    public void testCustomerQualifier() {
        movieRecommender.customerQualifier();

    }
}
