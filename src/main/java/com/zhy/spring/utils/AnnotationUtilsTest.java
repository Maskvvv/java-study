package com.zhy.spring.utils;

import com.zhy.spring.aop.controller.TransactionService;
import com.zhy.spring.aop.lock.AthenaLock;
import org.springframework.core.annotation.AnnotationUtils;

/**
 * AnnotationUtilsTest
 *
 * @author zhouhongyin
 * @since 2024/1/13 23:34
 */
public class AnnotationUtilsTest {

    public static void main(String[] args) {

        System.out.println(AnnotationUtils.isCandidateClass(TransactionService.class, AthenaLock.class));


    }
}
