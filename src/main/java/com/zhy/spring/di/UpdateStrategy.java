package com.zhy.spring.di;

import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2022/12/12 14:27
 */
@Component
public class UpdateStrategy extends TestStrategy{

    public TestStrategyEunm strategyEunm = TestStrategyEunm.Update;

    @Override
    public Object strategy() {
        return strategyEunm.getName();
    }

    @Override
    public TestStrategyEunm getStrategyEunm() {
        return strategyEunm;
    }
}
