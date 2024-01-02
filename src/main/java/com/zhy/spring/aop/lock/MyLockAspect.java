package com.zhy.spring.aop.lock;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.core.Ordered;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author zhouhongyin
 * @since 2023/3/2 22:18
 */
@Slf4j
@Aspect
@Component
public class MyLockAspect implements ApplicationContextAware, Ordered {

    private static ApplicationContext applicationContext;


    private final Map<Class<?>, MyLockProcessor> lockMap = new HashMap<>();
    private final Map<Class<?>, KeyConvert> keyConvertMap = new HashMap<>();

    public MyLockAspect(List<MyLockProcessor> lockList, List<KeyConvert> keyConvertList) {
        for (MyLockProcessor myLockProcessor : lockList) {
            lockMap.put(myLockProcessor.getClass(), myLockProcessor);
        }

        for (KeyConvert keyConvert : keyConvertList) {
            keyConvertMap.put(keyConvert.getClass(), keyConvert);
        }
    }


    @Pointcut("@annotation(com.zhy.spring.aop.lock.MyLock)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyLock annotation = method.getAnnotation(MyLock.class);

        String key = generateKey(joinPoint);

        return lockAndProceed(joinPoint, annotation, key);
    }

    private Object lockAndProceed(ProceedingJoinPoint joinPoint, MyLock annotation, String key) throws Throwable {

        Class<? extends MyLockProcessor> lockClazz = annotation.lockProcessor();
        MyLockProcessor myLockProcessor = lockMap.get(lockClazz);

        Object result;

        if (lockClazz == null) {
            synchronized (key.intern()) {
                result = joinPoint.proceed();
            }

        } else {
            try {
                if (annotation.leaseTime() > 0) {
                    myLockProcessor.lock(key, annotation.leaseTime());
                } else {
                    myLockProcessor.lock(key);
                }

                result = joinPoint.proceed();
            } finally {
                myLockProcessor.unlock(key);
            }
        }
        return result;
    }

    private String generateKey(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Method method = signature.getMethod();
        MyLock annotation = method.getAnnotation(MyLock.class);

        String prefix = annotation.prefix();
        String convertKey = parserKeyConvert(annotation, args);
        String spELKey = parserSpEL(parameterNames, args, annotation);
        return keyJoin(annotation.keySeparator(), annotation.ifKeyNull(), prefix, convertKey, spELKey);
    }

    private String parserKeyConvert(MyLock annotation, Object[] args) {
        Class<? extends KeyConvert>[] classes = annotation.keyConvert();
        if (ObjectUtils.isEmpty(classes)) {
            return "";
        }

        Class<? extends KeyConvert> keyConvertClass = classes[0];
        KeyConvert keyConvert = keyConvertMap.get(keyConvertClass);

        return keyConvert.getKey(args);
    }

    private String parserSpEL(String[] parameterNames, Object[] args, MyLock annotation) throws NoSuchMethodException {
        String expressionString = annotation.spEl();
        if (StringUtils.isEmpty(expressionString)) return expressionString;

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        context.setVariable("json", JSON.class.getMethod("toJSONString", Object.class));
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        return parser.parseExpression(expressionString, new TemplateParserContext("${", "}")).getValue(context, String.class);
    }

    private String keyJoin(String delimiter, KeyNull keyNull, String... keys) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < keys.length; i++) {
            String k = keys[i];
            if (!StringUtils.isEmpty(k)) {
                stringBuilder.append(k).append(delimiter);
            }
        }


        if (stringBuilder.length() > 1) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return stringBuilder.toString();
        }

        if (keyNull.equals(KeyNull.UUID)) {
            return UUID.randomUUID().toString();
        }

        throw new RuntimeException("lock key is null!");
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        MyLockAspect.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }
}

