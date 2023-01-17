package com.zhy.spring.ioc.extensionpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * spring example: AutowiredAnnotationBeanPostProcessor
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

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof BeanPostProcessorBean) {
            log.info("BeanPostProcessorBean-postProcessAfterInitialization:" + "Bean '" + beanName + "' created : " + bean.toString());
        }
        return bean;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {



    }
}
