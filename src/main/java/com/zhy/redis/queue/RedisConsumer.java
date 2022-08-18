package com.zhy.redis.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2022/8/9 16:52
 */
@Slf4j
@Component
public class RedisConsumer {

    public static final String REDIS_ROUTING_KEY = "file:transfer";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ThreadPoolTaskExecutor redisQueueTaskExecutor;

    @PostConstruct
    public void initConsumer() {
        redisQueueTaskExecutor.execute(this::consumer);
    }

    public void consumer() {
        while (true) {
            try {
                String s = stringRedisTemplate.opsForList().leftPop(REDIS_ROUTING_KEY);
                if (StringUtils.isNotBlank(s)) {
                    log.info(Thread.currentThread() + "RedisConsumer:{}", s);
                }

            } catch (Exception e) {
                //log.error(e.getMessage());
            }
        }

    }

}
