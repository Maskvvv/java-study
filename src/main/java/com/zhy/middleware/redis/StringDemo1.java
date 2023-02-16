package com.zhy.middleware.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class StringDemo1 {

    @Test
    public void test(){
        //1.连接Redis
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        //2.操作Redis
        jedis.set("name","李四");
        //3.释放资源
        jedis.close();
    }
}
