package com.zhy.middleware.redis;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class SetObjectByJsonDemo3 {

    @Test
    public void setString(){
        //1.连接Redis服务
        Jedis jedis = new Jedis(RedisHostEnum.HSOT.host, 6379);

        //2.1准备key(string)-value(User)
        String stringKey = "user";
        User value = new User(1, "mike", new Date());
        //2.2使用fastJSON转化为json字符串
        String stringValue = JSON.toJSONString(value);
        //2.3将饿key和value存储到Redis
        jedis.set(stringKey,stringValue);

        //3.释放资源
        jedis.close();
    }


    @Test
    public void getString(){
        //1.连接Redis服务
        Jedis jedis = new Jedis(RedisHostEnum.HSOT.host, 6379);

        //2.1准备key
        String stringKey = "user";
        //2.2获取value
        String value = jedis.get(stringKey);
        //2.3将value反序列化为User
        User user = JSON.parseObject(value, User.class);
        //2.4输出
        System.out.println(user);

        //3.释放资源
        jedis.close();
    }
}
