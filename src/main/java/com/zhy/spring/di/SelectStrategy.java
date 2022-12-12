package com.zhy.spring.di;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2022/12/12 14:27
 */
@Data
@Component
public class SelectStrategy extends TestStrategy{

    public TestStrategyEunm strategyEunm = TestStrategyEunm.Select;

    @Override
    public Object strategy() {
        return strategyEunm.getName();
    }
}
