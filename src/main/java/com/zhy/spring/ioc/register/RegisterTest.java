package com.zhy.spring.ioc.register;

import com.zhy.model.git.entity.People;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * beanDefinition 两种注册方式
 *
 * @author zhouhongyin
 * @since 2023/5/24 22:03
 */
public class RegisterTest {

    public static void main(String[] args) {
        // 命名方式注册 bean
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(People.class);
        beanDefinitionBuilder.addPropertyValue("name", "zhy");
        AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();

        BeanDefinitionRegistry beanDefinitionRegistry = new DefaultListableBeanFactory();
        beanDefinitionRegistry.registerBeanDefinition("user-zhy", rawBeanDefinition);

        // 非命名方式注册 bean
        BeanDefinitionReaderUtils.registerWithGeneratedName(rawBeanDefinition, beanDefinitionRegistry);


    }
}
