package com.zhy.spring.ioc.lifecycle;

import com.zhy.spring.ioc.extensionpoint.BeanPostProcessorBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

/**
 * bean 的初始化和销毁
 *
 * @author zhouhongyin
 * @since 2023/1/3 15:49
 */
@Slf4j
@Component
public class LifeCycleCallbacks implements InitializingBean, DisposableBean, BeanPostProcessor {

    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof BeanPostProcessorBean processorBean) {
            log.info("LifeCycleCallbacks-postProcessBeforeInitialization:" + "Bean '" + beanName + "' created : " + bean.toString());

            Class<? extends BeanPostProcessorBean> processorBeanClass = processorBean.getClass();
            Field nameFiled = processorBeanClass.getDeclaredField("name");
            nameFiled.setAccessible(true);
            nameFiled.set(processorBean, "postProcessAfterInitialization");
        }

        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @PostConstruct
    private void postConstruct() {
        log.info("LifeCycleCallbacks-postConstruct");
    }

    /**
     * 在 spring 容器设置完当前 bean 的必要属性后会执行改方法
     * initialization work after the container has set all necessary properties on the bean
     *
     * 不建议使用，因为这样会把代码耦合金spring。建议用 @PostConstruct 或者 @Bean 的 initMethod 属性代替
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("LifeCycleCallbacks-afterPropertiesSet");

    }

    /**
     * 不建议使用，建议通过 @PreDestroy 或者 @Bean 的 destroyMethod 代替
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {

    }
}
