package com.zhy.spring.ioc.extensionpoint;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
 * spring example: AutowiredAnnotationBeanPostProcessor
 *
 * 触发时机不同：BeanFactoryPostProcessor 触发时机是在所有 Bean 实例化之前，对 BeanFactory 进行配置，而 BeanPostProcessor 是对每个 Bean 对象进行初始化之前和之后进行处理。
 * 处理的内容不同：BeanFactoryPostProcessor 用于对 BeanFactory 配置元数据进行修改，比如修改 BeanDefinition 中的属性值，可以添加、删除、修改 Bean 的定义。而 BeanPostProcessor 是用于在 Bean 实例化之后，对 Bean 进行增强，可以修改 Bean 实例的属性值，AOP 的动态代理就是其中一种实现方式。
 * 程序员使用场景不同：BeanFactoryPostProcessor 通常由框架提供，是框架内部的处理机制，可以对 BeanFactory 进行扩展。而 BeanPostProcessor 是由开发者自己编写实现类来实现具体的增强逻辑，比如事务管理等。
 *
 * @author zhouhongyin
 * @since 2023/1/4 10:16
 */
@Slf4j
@Component
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor, BeanFactoryPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @SneakyThrows
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof BeanPostProcessorBean processorBean) {
            log.info("BeanPostProcessorBean-postProcessAfterInitialization:" + "Bean '" + beanName + "' created : " + bean.toString());

            Class<? extends BeanPostProcessorBean> processorBeanClass = processorBean.getClass();
            Field nameFiled = processorBeanClass.getDeclaredField("name");
            nameFiled.setAccessible(true);
            nameFiled.set(processorBean, "postProcessAfterInitialization");
        }

        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {



    }
}
