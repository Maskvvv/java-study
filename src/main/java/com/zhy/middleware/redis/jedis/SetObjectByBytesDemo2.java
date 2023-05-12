package com.zhy.middleware.redis.jedis;

import com.zhy.middleware.redis.User;
import org.junit.Test;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;

import java.util.Date;

public class SetObjectByBytesDemo2 {

    @Test
    public void setByteArray(){
        //1.连接Redis服务
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //2.1准备key(string)-value(User)
        String key = "user";
        User value = new User(1, "mike", new Date());
        //2.2将key和value转换为byte[]
        byte[] byteKey = SerializationUtils.serialize(key);
        byte[] byteValue = SerializationUtils.serialize(value);
        //2.3将饿key和value存储到Redis
        jedis.set(byteKey,byteValue);

        //3.释放资源
        jedis.close();
    }


    @Test
    public void getByteArray(){
        //1.连接Redis服务
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        //2.1准备key
        String key = "user";
        //2.2将key转换为byte[]
        byte[] byteKey = SerializationUtils.serialize(key);
        //2.3jedis去Redis中获取value
        byte[] value = jedis.get(byteKey);
        //2.4将value反序列化为对象
        User user = (User) SerializationUtils.deserialize(value);
        //2.5输出
        System.out.println(user);

        //3.释放资源
        jedis.close();
    }
}
