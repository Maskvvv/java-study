package com.zhy.spring.ioc.beandefinition;

import com.zhy.model.git.entity.People;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * BeanDefinition 两种创建方式
 *
 * @author zhouhongyin
 * @since 2023/5/24 21:36
 */
public class BeanDefinitionTest {

    public static void main(String[] args) {
        // 通过 BeanDefinitionBuilder 创建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(People.class);
        beanDefinitionBuilder.addPropertyValue("name", "zhy");
        AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        // 通过 GenericBeanDefinition 创建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(People.class);
        genericBeanDefinition.setAttribute("name", "zhy");

    }

}
