package com.zhy.middleware.redis.queue;

import org.junit.Test;
import redis.clients.jedis.DefaultJedisClientConfig;
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
        String host = System.getProperty("host");
        String password = System.getProperty("password");
        DefaultJedisClientConfig config = DefaultJedisClientConfig.builder().password(password).build();
        Jedis jedis = new Jedis(host, 6380, config);

        for (int i = 0; i < 10; i++) {

            jedis.rpush(RedisConsumer.REDIS_ROUTING_KEY + 1, "我是消息" + i);
        }


    }
}
