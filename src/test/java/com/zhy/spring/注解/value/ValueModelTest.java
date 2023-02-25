package com.zhy.spring.注解.value;

import com.zhy.JavaStudyApplication;
import com.zhy.spring.注解.configurationproperties.ConfigurationPropertiesModel;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2023/1/10 22:39
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class ValueModelTest extends TestCase {

    @Resource
    private ValueModel valueModel;

    @Test
    public void test1() {

        System.out.println(valueModel);
    }

    @Resource
    private ConfigurationPropertiesModel configurationPropertiesModel;

    @Test
    public void test2() {

        System.out.println(configurationPropertiesModel);
    }


}
