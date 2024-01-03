package com.zhy.spring.aop.lock;

import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * <p> spELContext 后置处理器 </p>
 *
 * @author zhouhongyin
 * @since 2024/1/2 14:58
 */
public interface AthenaLockSpELContextPostProcessor {

    void postProcess(StandardEvaluationContext context);

}
