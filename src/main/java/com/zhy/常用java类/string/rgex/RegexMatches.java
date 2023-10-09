package com.zhy.常用java类.string.rgex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
    public static void main(String[] args) {

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
}
