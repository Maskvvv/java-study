package com.zhy.spring.di;

/**
 * @author zhouhongyin
 * @since 2022/12/12 14:22
 */
public abstract class TestStrategy {

    private TestStrategyEunm strategyEunm;

    public abstract Object strategy();

    public TestStrategyEunm getStrategyEunm() {
        return strategyEunm;
    }
}
