package com.zhy.disign.pattern.creational.builder;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class BuilderTest {

    @Test
    public void builderTest(){
        Map<String, String> map = new HashMap<>();
        map.put("a", "123");
        map.put("q", "K&R");
        String url = URLBuilder.builder()
                .setDomain("www.liaoxuefeng.com")
                .setScheme("https")
                .setPath("/")
                .setQuery(map)
                .build();

        System.out.println(url);
    }

}
