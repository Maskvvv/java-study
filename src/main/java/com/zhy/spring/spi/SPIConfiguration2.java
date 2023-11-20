package com.zhy.spring.spi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;

/**
 * <p> Ordered Test </p>
 *
 * @author zhouhongyin
 * @since 2023/11/16 17:52
 */
@Slf4j
//@Configuration
public class SPIConfiguration2 implements Ordered, BeanDefinitionRegistryPostProcessor {

    public SPIConfiguration2() {
        log.info("SPIConfiguration2 init, order={}", getOrder());
    }

    @Bean
    public SPIBean orderCloud2() {
        log.info("SPIConfiguration2 orderCloud bean init");

        return new SPIBean();
    }


    @Override
    public int getOrder() {
        return SPIOrder.ORDER_CLOUD;
    }
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("SPIConfiguration2 postProcessBeanDefinitionRegistry");

    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }


}
