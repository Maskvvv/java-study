package com.zhy.middleware.redis.queue;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouhongyin
 * @since 2022/8/9 16:52
 */
@Slf4j
@Component
@ConditionalOnProperty(prefix = "zhy", name = "redis-consumer.enable", havingValue = "true")
public class RedisConsumer {

    public static final String REDIS_ROUTING_KEY = "file:transfer";

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private ThreadPoolTaskExecutor redisQueueTaskExecutor;

    @PostConstruct
    public void initConsumer() {
        redisQueueTaskExecutor.execute(this::consumer1);
        redisQueueTaskExecutor.execute(this::consumer2);
        redisQueueTaskExecutor.execute(this::consumer3);
    }

    public void consumer1() {
        log.info(Thread.currentThread().getName() + "----------------------- RedisConsumer1");
        while (true) {
            try {
                String s = stringRedisTemplate.opsForList().leftPop(REDIS_ROUTING_KEY + 1, 0, TimeUnit.SECONDS);

                if (StringUtils.isNotBlank(s)) {
                    log.info(Thread.currentThread() + "RedisConsumer:{}", s);
                }

            } catch (Exception e) {
                //log.error(e.getMessage());
            }
        }

    }

    public void consumer2() {
        log.info(Thread.currentThread().getName() + "----------------------- RedisConsumer2");
        while (true) {
            try {
                String s = stringRedisTemplate.opsForList().leftPop(REDIS_ROUTING_KEY + 2, 0, TimeUnit.SECONDS);

                if (StringUtils.isNotBlank(s)) {
                    log.info(Thread.currentThread() + "RedisConsumer:{}", s);
                }

            } catch (Exception e) {
                //log.error(e.getMessage());
            }
        }

    }

    public void consumer3() {
        log.info(Thread.currentThread().getName() + "----------------------- RedisConsumer3");
        while (true) {
            try {
                String s = stringRedisTemplate.opsForList().leftPop(REDIS_ROUTING_KEY + 3);

                if (StringUtils.isNotBlank(s)) {
                    log.info(Thread.currentThread() + "RedisConsumer:{}", s);
                }

            } catch (Exception e) {
                //log.error(e.getMessage());
            }
        }

    }

}
