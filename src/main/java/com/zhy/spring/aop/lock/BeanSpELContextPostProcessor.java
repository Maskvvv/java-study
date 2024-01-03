package com.zhy.spring.aop.lock;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/2 15:01
 */
@Component
public class BeanSpELContextPostProcessor implements AthenaLockSpELContextPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void postProcess(StandardEvaluationContext context) {
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
