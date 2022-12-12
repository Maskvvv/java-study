package com.zhy.spring.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouhongyin
 * @since 2022/12/12 14:31
 */
@RestController
@RequestMapping("test_strategy")
public class TestStrategyController {
    private final Map<TestStrategyEunm, TestStrategy> strategyMap;
    private final List<TestStrategy> strategyList;

    @Autowired
    public TestStrategyController(List<TestStrategy> strategyList) {
        this.strategyList = strategyList;
        this.strategyMap = new ConcurrentHashMap<>();
        init();
    }

    @PostConstruct
    public void init() {
        for (TestStrategy testStrategy : strategyList) {
            strategyMap.put(testStrategy.getStrategyEunm(), testStrategy);
        }
    }

    @GetMapping
    public String strategy(TestStrategyEunm strategyEunm) {
        for (TestStrategy testStrategy : strategyList) {
            System.out.println(testStrategy.getStrategyEunm().getName());
        }

        //Object strategy = strategyList.stream().filter(s -> s.getStrategyEunm().equals(strategyEunm)).findFirst().orElse(null).strategy();
        //return (String) strategy;
        return (String) strategyMap.get(strategyEunm).strategy();
    }

}
