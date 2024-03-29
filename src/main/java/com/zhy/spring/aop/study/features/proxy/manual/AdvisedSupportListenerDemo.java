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

import com.zhy.spring.aop.study.common.DefaultEchoService;
import com.zhy.spring.aop.study.common.EchoService;
import com.zhy.spring.aop.study.features.advice.EchoServiceMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;

/**
 * {@link AdvisedSupportListener} 示例
 */
public class AdvisedSupportListenerDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTargetClass(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        proxyFactory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                System.out.println("AOP 配置对象：" + advised + " 已激活");
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("AOP 配置对象：" + advised + " 已变化");
            }
        });
        // 获取代理对象
        // 激活事件触发 createAopProxy() <- getProxy()
        // 只有 getProxy() 后才能激活后续的 Listener 事件
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        proxyFactory.addAdvice(new Advice() {
        });
    }
}
