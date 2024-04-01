package com.zhy.常用java类.string.rgex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zhouhongyin
 * @since 2024/4/1 13:49
 */
public class ApiTest {


    @Test
    public void appendReplacement() {

        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }

        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
