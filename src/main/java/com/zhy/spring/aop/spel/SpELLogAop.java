package com.zhy.spring.aop.spel;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Bean;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author zhouhongyin
 * @since 2023/3/2 22:18
 */
@Slf4j
@Aspect
@Component
public class SpELLogAop {

    @Resource
    private ThreadPoolTaskExecutor spelThreadPoolTaskExecutor;

    @Bean
    public ThreadPoolTaskExecutor spelThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(1);threadPoolTaskExecutor.setQueueCapacity(200);
        threadPoolTaskExecutor.setThreadNamePrefix("methodLog-");
        return threadPoolTaskExecutor;
    }

    @Pointcut("@annotation(com.zhy.spring.aop.spel.MethodLog)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        Object proceed = joinPoint.proceed();

        spelThreadPoolTaskExecutor.execute(() -> {
            try {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();

                String[] parameterNames = signature.getParameterNames();
                Object[] args = joinPoint.getArgs();
                Method method = signature.getMethod();
                MethodLog annotation = method.getAnnotation(MethodLog.class);
                String content = parser(parameterNames, args, annotation);

                String className = joinPoint.getTarget().getClass().getSimpleName();
                String methodName = method.getName();


                log.info("Class {}#{}() {}", className, methodName, content);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });

        return proceed;
    }

    public static String parser(String[] parameterNames, Object[] args, MethodLog annotation) throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        context.setVariable( "json", JSON.class.getMethod("toJSONString", Object.class));

        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }

        return parser.parseExpression(annotation.content(), new TemplateParserContext()).getValue(context, String.class);
    }

}
