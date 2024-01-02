package com.zhy.spring.aop.lock;

import com.zhy.spring.spEL.model.DomainKey;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/2 15:01
 */
@Component
public class BeanSpELContextPostProcessor implements MyLockSpELContextPostProcessor {


    @Override
    public void postProcess(StandardEvaluationContext context) {
        for (DomainKey value : DomainKey.values()) {
            context.setVariable(value.name(), value);
        }
    }
}
