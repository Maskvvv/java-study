package com.zhy.spring.aop.lock;

import com.alibaba.fastjson.JSON;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/2 15:01
 */
@Component
public class JsonSpELContextPostProcessor implements AthenaLockSpELContextPostProcessor {


    @Override
    public void postProcess(StandardEvaluationContext context) {
        try {
            context.setVariable("json", JSON.class.getMethod("toJSONString", Object.class));
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
