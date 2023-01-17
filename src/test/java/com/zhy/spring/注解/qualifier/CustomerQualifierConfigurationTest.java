package com.zhy.spring.注解.qualifier;

import com.zhy.JavaStudyApplication;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhouhongyin
 * @since 2023/1/17 14:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class CustomerQualifierConfigurationTest extends TestCase {

    //@Resource
    //@Qualifier("actionVhsCatalog2")
    //private MovieCatalog movieCatalog;
    //
    //@Resource
    //@Qualifier("actionVhsCatalog3")
    //private MovieCatalog movieCatalog1;
    //
    //@Test
    //public void testActionVhsCatalog1() {
    //    System.out.println(movieCatalog.getName());
    //
    //    System.out.println(movieCatalog1);
    //
    //}
}
