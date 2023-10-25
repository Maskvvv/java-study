package com.zhy.spring.annotation.ConditionalOnBean.test;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/10/10 9:52
 */
//@Service
public class ConditionService {

    private final Bean2 bean2;

    public ConditionService(Bean2 bean2) {
        this.bean2 = bean2;
    }
}
