package com.zhy.spring.ioc.aware;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2023/1/12 17:14
 */
@Data
@Slf4j
@Component
public class ApplicationContextAwareBean implements ApplicationContextAware, BeanNameAware {
    private String name = "ApplicationContextAwareBean";

    @Resource
    private ApplicationContext myApplicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Object applicationContextAwareBean = applicationContext.getBean("applicationContextAwareBean");
        log.info("ApplicationContextAwareBean-setApplicationContext:{}", applicationContextAwareBean);

        Object myApplicationContextAwareBean = myApplicationContext.getBean("applicationContextAwareBean");
        log.info("ApplicationContextAwareBean-setApplicationContext:{}", myApplicationContextAwareBean);
    }

    @Override
    public void setBeanName(String name) {
        log.info("ApplicationContextAwareBean-setBeanName:{}", name);

    }
}
