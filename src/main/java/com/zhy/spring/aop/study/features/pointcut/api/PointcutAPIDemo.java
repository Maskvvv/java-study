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
package com.zhy.spring.aop.study.features.pointcut.api;


import com.zhy.spring.aop.study.common.DefaultEchoService;
import com.zhy.spring.aop.study.common.EchoService;
import com.zhy.spring.aop.study.features.advice.EchoServiceMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

/**
 * API实现Pointcut
 *
 * <pre>
 *     Advisor 是 Advice 和 Pointcut 则组合
 * </pre>
 *
 * @see Advisor
 * @see Advice
 * @see Pointcut
 * @see PointcutAdvisor
 * @see StaticMethodMatcherPointcut 静态 pointCut 值判断 method
 * @see JdkRegexpMethodPointcut 正则表达式 poinCut
 * @see ControlFlowPointcut 控制流 pointCut
 * @see AspectJExpressionPointcut 支持 AspectJExpress pointut
 */
public class PointcutAPIDemo {

    public static void main(String[] args) {

        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        // 组合（与或非）多个 pointCut
        ComposablePointcut pointcut = new ComposablePointcut();
        // 组合实现
        //pointcut.intersection(echoServicePointcut.getClassFilter());
        //pointcut.intersection(echoServicePointcut.getMethodMatcher());
        pointcut.intersection((Pointcut) echoServicePointcut);
        pointcut.intersection(EchoServiceEchoMethodPointcut.INSTANCE);

        // 将 Pointcut 适配成 Advisor
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());




        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello,World"));
    }
}
