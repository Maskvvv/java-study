package com.zhy.redis.queue;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @author zhouhongyin
 * @since 2022/8/10 9:23
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = JavaStudyApplication.class)
public class RedisConsumerTest {

    @Test
    public void testConsumer() {

        Jedis jedis = new Jedis("127.0.0.1", 6379);

        for (int i = 0; i < 10; i++) {

            jedis.rpush(RedisConsumer.REDIS_ROUTING_KEY + 2, "我是消息" + i);
        }


    }
}
