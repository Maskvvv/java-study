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
 *
 * @author zhouhongyin
 * @since 2023/11/16 17:52
 */
@Slf4j
//@Configuration
public class SPIConfiguration1 implements Ordered, BeanDefinitionRegistryPostProcessor {

    public SPIConfiguration1() {
        log.info("SPIConfiguration1 init, order={}", getOrder());
    }


    @Bean
    public SPIBean orderCloud1() {
        log.info("SPIConfiguration1 orderCloud bean init");

        return new SPIBean();
    }

    @Override
    public int getOrder() {
        return SPIOrder.ORDER_LOCAL;
    }
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        log.info("SPIConfiguration1 postProcessBeanDefinitionRegistry");

    }
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }


}
