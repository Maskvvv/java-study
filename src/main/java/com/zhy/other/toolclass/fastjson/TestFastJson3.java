package com.zhy.other.toolclass.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @author zhouhongyin
 * @since 2021/7/23 11:37
 */

public class TestFastJson3 {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(10);
        user.setName("zhy");
        String jsonString = JSON.toJSONString(user);
        System.out.println("序列化对象"+jsonString);

        JSONObject jsonObject = JSON.parseObject(jsonString);
        System.out.println(jsonObject);


    }

}
