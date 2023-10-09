package com.zhy.常用java类.string.rgex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> Matcher Test </p>
 *
 * @author zhouhongyin
 * @since 2023/10/9 11:25
 */
public class MatcherTest {
    @Test
    public void test1() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        while (m.find()) {
            //System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group());
            System.out.println("Found value: " + m.group(1));
        }
    }

    @Test
    public void group() {

        String s = "12345678";
        Pattern pattern = Pattern.compile("(\\d)(?=(\\d{3})+$)");

        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }
    }

    @Test
    public void groupReplace() {
        String s = "12345678";
        Pattern pattern = Pattern.compile("(\\d)(?=(\\d{3})+$)");
        System.out.println(pattern.matcher(s).replaceAll("$1,"));
    }

}
