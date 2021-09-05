package com.zhy.toolclass.fastjson;

import com.alibaba.fastjson.JSON;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/7/23 11:37
 */

public class TestFastJson1 {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<String, String>(16);
        map.put("name", "zhy");
        map.put("data", new Date().toString());

        String s = JSON.toJSONString(map);
        System.out.println(s);
    }

}
