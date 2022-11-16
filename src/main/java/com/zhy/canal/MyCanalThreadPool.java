package com.zhy.canal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zhouhongyin
 * @since 2022/11/16 10:55
 */

@Configuration
public class MyCanalThreadPool {

    @Bean
    public ThreadPoolTaskExecutor canalThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(1);

        return threadPoolTaskExecutor;

    }

}
