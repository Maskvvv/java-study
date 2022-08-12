package com.zhy.redis.queue;

import com.zhy.JavaStudyApplication;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhouhongyin
 * @since 2022/8/10 9:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class RedisConsumerTest extends TestCase {


    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void testConsumer() {
        stringRedisTemplate.opsForValue().set(RedisConsumer.REDIS_ROUTING_KEY, "我是消息");


    }
}
