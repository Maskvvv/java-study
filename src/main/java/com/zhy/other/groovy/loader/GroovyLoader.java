package com.zhy.other.groovy.loader;

import groovy.lang.GroovyClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.groovy.control.CompilerConfiguration;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2023/5/20 11:54
 */
@Slf4j
@Component
public class GroovyLoader implements ApplicationContextAware {

    private static final GroovyClassLoader groovyClassLoader;

    public static final String UTF_8 = "UTF-8";

    private ApplicationContext ctx;

    static {
        CompilerConfiguration compilerConfiguration = new CompilerConfiguration();
        compilerConfiguration.setSourceEncoding(UTF_8);
        groovyClassLoader = new GroovyClassLoader(Thread.currentThread().getContextClassLoader(), compilerConfiguration);
    }


    public Object getBean(String beanName, String scriptBase64) {

        // 1. 尝试去 spring 容器中 获取 beanName 的 bean
        Object bean = getBeanInner(beanName);
        if (bean != null) return bean;

        // 2. 针对 java code 进行编译，拿到 Class
        Class clz = compile(scriptBase64);

        // 3. 将拿到的 Class 对象交给 spring 容器实例化
        applyClz2Spring(beanName, clz);

        // 4. 再次从spring 容器中获取 bean 返回
        bean = getBeanInner(beanName);
        return bean;
    }

    private Class compile(String scriptBase64) {
        String script = new String(Base64.decodeBase64(scriptBase64), StandardCharsets.UTF_8);
        return groovyClassLoader.parseClass(script);
    }

    private void applyClz2Spring(String beanName, Class clz) {

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(clz).getRawBeanDefinition();
        ((BeanDefinitionRegistry) ((AbstractApplicationContext) ctx).getBeanFactory()).registerBeanDefinition(beanName, beanDefinition);
    }

    private Object getBeanInner(String beanName) {
        try {
            Object bean = ctx.getBean(beanName);
            return bean;
        } catch (BeansException e) {
            log.info("没有该 bean");
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;

    }
}
