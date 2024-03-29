/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhy.spring.aop.study.features.proxy.manual;

import com.zhy.spring.aop.study.common.EchoService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p> XML配置驱动 - 创建AOP代理 </p>
 * ProxyFactoryBean 用来和 Spring IOC 做整合
 *
 * @see ProxyFactoryBean
 */
@Aspect        // 声明为 Aspect 切面
@Configuration // Configuration class
public class ProxyFactoryBeanDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/META-INF/study/aop/spring-aop-context.xml");

        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

        System.out.println(echoService.echo("Hello,World"));

        context.close();
    }
}
