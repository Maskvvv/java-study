package com.zhy.toolclass.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 *  
 * @author zhouhongyin
 * @since 2021/7/23 11:37
 */

public class TestFastJson2 {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(10);
        user.setName("zhy");
        String jsonString = JSON.toJSONString(user, SerializerFeature.WriteClassName);
        System.out.println("序列化对象"+jsonString);

        User parse = (User) JSON.parse(jsonString);
        User user1 = JSON.parseObject(jsonString, User.class);
        System.out.println("反序列化对象：" + parse);

    }

}
