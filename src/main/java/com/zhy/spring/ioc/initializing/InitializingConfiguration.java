package com.zhy.spring.ioc.initializing;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * 1.InitializingBean接口只包含一个afterPropertiesSet()方法，
 * 用于在Bean属性设置完毕后执行一些初始化操作，而SmartInitializingSingleton
 * 接口中只有一个afterSingletonsInstantiated()方法，用于在所有单例Bean都实例化后执行一些初始化操作。
 * </p>
 *
 * <p>
 * 2.SmartInitializingSingleton接口的实现类可以在初始化时获取所有的单例Bean，
 * 而InitializingBean接口的实现类只能获取对应Bean的属性值。
 * </p>
 *
 * <p>
 * 3.SmartInitializingSingleton接口的实现类可以与@DependsOn注解结合使用，以确保其依赖的Bean在其之前实例化。
 * </p>
 *
 *
 * <p>
 * 总的来说，SmartInitializingSingleton更加灵活，能够处理更多的场景，而InitializingBean则更加简单，只适用于简单的Bean初始化操作。
 * </P>
 *
 * @author zhouhongyin
 * @since 2023/6/1 23:03
 */
@Configuration
public class InitializingConfiguration implements InitializingBean, SmartInitializingSingleton {
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void afterSingletonsInstantiated() {

    }
}
