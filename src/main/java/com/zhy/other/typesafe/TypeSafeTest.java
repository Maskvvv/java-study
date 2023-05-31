package com.zhy.other.typesafe;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigValue;
import org.junit.Test;

import java.util.List;
import java.util.Map;

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

    @Test
    public void test() {
        Config config = ConfigFactory.parseResources("type.conf");
        ConfigObject root = config.root();
        for (Map.Entry<String, ConfigValue> rootEntry : root.entrySet()) {
            System.out.println(rootEntry.getKey());
            String username = rootEntry.getValue().atKey("username").toString();
            System.out.println(username);

        }

        //for (Map.Entry<String, ConfigValue> entry : config.entrySet()) {
        //    System.out.println(entry.getKey());
        //}

        Config mysql = config.getConfig("mysql");
        System.out.println(mysql.getString("username"));


    }

}
