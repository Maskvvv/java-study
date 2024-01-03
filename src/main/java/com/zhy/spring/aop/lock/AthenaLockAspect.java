package com.zhy.spring.aop.lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author zhouhongyin
 * @since 2023/3/2 22:18
 */
@Slf4j
@Aspect
@Component
public class AthenaLockAspect implements Ordered {

    private final AthenaLockProcessor lock;
    private final Map<Class<?>, AthenaLockProcessor> lockMap = new HashMap<>();
    private final Map<Class<?>, KeyConvert> keyConvertMap = new HashMap<>();
    private final List<AthenaLockSpELContextPostProcessor> contextPostProcessors;

    public AthenaLockAspect(List<AthenaLockProcessor> lockList, List<KeyConvert> keyConvertList,
                            @Autowired(required = false) AthenaLockProcessor lock, List<AthenaLockSpELContextPostProcessor> contextPostProcessors) {
        this.lock = lock;
        this.contextPostProcessors = contextPostProcessors;
        for (AthenaLockProcessor athenaLockProcessor : lockList) {
            lockMap.put(athenaLockProcessor.getClass(), athenaLockProcessor);
        }

        for (KeyConvert keyConvert : keyConvertList) {
            keyConvertMap.put(keyConvert.getClass(), keyConvert);
        }
    }


    @Pointcut("@annotation(com.zhy.spring.aop.lock.AthenaLock)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        AthenaLock annotation = method.getAnnotation(AthenaLock.class);

        String key = generateKey(joinPoint);

        return lockAndProceed(joinPoint, annotation, key);
    }

    private Object lockAndProceed(ProceedingJoinPoint joinPoint, AthenaLock annotation, String key) throws Throwable {
        Class<? extends AthenaLockProcessor>[] lockClazzs = annotation.lockProcessor();
        AthenaLockProcessor lockProcessor = lock;
        if (lockClazzs.length > 0) {
            lockProcessor = lockMap.get(lockClazzs[0]);
        }

        Object result;

        if (lockProcessor == null) {
            synchronized (key.intern()) {
                result = joinPoint.proceed();
            }

        } else {
            try {
                if (annotation.leaseTime() > 0) {
                    lockProcessor.lock(key, annotation.leaseTime());
                } else {
                    lockProcessor.lock(key);
                }

                result = joinPoint.proceed();
            } finally {
                lockProcessor.unlock(key);
            }
        }
        return result;
    }

    private String generateKey(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();
        Method method = signature.getMethod();
        AthenaLock annotation = method.getAnnotation(AthenaLock.class);

        String prefix = annotation.prefix();
        String convertKey = parserKeyConvert(annotation, args);
        String spELKey = parserSpEL(parameterNames, args, annotation);
        return keyJoin(annotation.keySeparator(), prefix, convertKey, spELKey);
    }

    private String parserKeyConvert(AthenaLock annotation, Object[] args) {
        Class<? extends KeyConvert>[] classes = annotation.keyConvert();
        if (ObjectUtils.isEmpty(classes)) {
            return "";
        }

        Class<? extends KeyConvert> keyConvertClass = classes[0];
        KeyConvert keyConvert = keyConvertMap.get(keyConvertClass);

        return keyConvert.getKey(args);
    }

    private String parserSpEL(String[] parameterNames, Object[] args, AthenaLock annotation) {
        String expressionString = annotation.spEl();
        if (StringUtils.isEmpty(expressionString)) return expressionString;

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        for (AthenaLockSpELContextPostProcessor contextPostProcessor : contextPostProcessors) {
            contextPostProcessor.postProcess(context);
        }

        return parser.parseExpression(expressionString, new TemplateParserContext()).getValue(context, String.class);
    }

    private String keyJoin(String delimiter, String... keys) {
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

        throw new RuntimeException("lock key is null!");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }
}

