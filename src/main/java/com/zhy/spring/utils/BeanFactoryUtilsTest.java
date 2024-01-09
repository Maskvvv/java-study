package com.zhy.spring.utils;

import org.springframework.aop.Advisor;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanFactoryUtils test
 *
 * @author zhouhongyin
 * @since 2024/1/9 22:22
 */
public class BeanFactoryUtilsTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        String[] advisorName = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(
                applicationContext.getBeanFactory(), Advisor.class, true, true);
    }
}
