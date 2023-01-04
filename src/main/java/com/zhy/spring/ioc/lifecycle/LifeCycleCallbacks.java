package com.zhy.spring.ioc.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 *
 * @author zhouhongyin
 * @since 2023/1/3 15:49
 */
@Component
public class LifeCycleCallbacks implements InitializingBean, DisposableBean {


    /**
     * 在 spring 容器设置完 bean 的必要属性后会执行改方法
     * initialization work after the container has set all necessary properties on the bean
     *
     * 不建议使用，因为这样会把代码耦合金spring。建议用 @PostConstruct 或者 @Bean 的 initMethod 属性代替
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    /**
     * 不建议使用，建议通过 @PreDestroy 或者 @Bean 的 destroyMethod 代替
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {

    }
}
