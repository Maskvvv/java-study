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
 * <p>bean 的初始化和销毁</p>
 *
 * <p>InitializingBean 和 BeanPostProcessor 的区别</p>
 *
 * <p>
 * 1. 初始化时机不同：InitializingBean 的初始化方法是在 bean 属性设置完毕后，容器实例化该 bean 后立即调用；
 * 而 BeanPostProcessor 的初始化方法是在 bean 实例化后，初始化前调用。
 * InitializingBean 只会在实现了该接口的 Bean 中执行一次，而 BeanPostProcessor 会在每个 Bean  set 玩属性后都会执行
 * </p>
 *
 * <p>
 * 2. 功能不同：InitializingBean 的作用是在 bean 属性设置完毕后，对 bean 进行一些初始化操作；
 * 而 BeanPostProcessor 的作用是在 bean 初始化前后进行一些自定义处理，例如修改 bean 的属性，或者添加一些自定义的初始化逻辑等。
 * </p>
 *
 * <p>
 * 3. 使用场景不同：如果需要在 bean 属性设置完毕后进行初始化操作，可以使用 InitializingBean；而
 * 如果需要在 bean 初始化前后进行一些自定义处理，可以使用 BeanPostProcessor。
 * </p>
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
