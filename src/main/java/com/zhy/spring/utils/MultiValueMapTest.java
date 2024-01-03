package com.zhy.spring.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/3 17:06
 */
public class MultiValueMapTest {

    public static void main(String[] args) {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("a", "1");
        map.add("a", "2");

        List<String> a = map.get("a");
        System.out.println(a);

    }

}
