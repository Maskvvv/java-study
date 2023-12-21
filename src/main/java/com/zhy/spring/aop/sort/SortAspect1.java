package com.zhy.spring.aop.sort;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 9:24
 */
@Service
@Aspect
public class SortAspect1 {


    @Pointcut("execution(* com.zhy.spring.aop.controller.TransactionController.sort(..))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("SortAspect1 begin!");
        Object proceed = joinPoint.proceed();
        System.out.println("SortAspect1 end!");

        return proceed;
    }

}
