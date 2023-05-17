package com.zhy.other.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/5/17 16:59
 */
public class TypeSafeTest {

    public static void main(String[] args) {
        String configInfo = "";
        Config load = ConfigFactory.parseString(configInfo);


        String mapping = load.getString("mapping");
        System.out.println(mapping);

        List<String> like = load.getStringList("like");
        for (String s : like) {
            System.out.println(s);
        }

    }

}
