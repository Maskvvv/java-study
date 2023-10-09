package com.zhy.常用java类.string.rgex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/10/7 16:06
 */
public class RgexTest {

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

    @Test
    public void stringReplace() {
        // 脱敏
        String tel = "18304072984";
        // 括号表示组，被替换的部分$n表示第n组的内容
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println(tel);   // output: 183****2984

        // 交换单词
        String one = "hello girl hi hot".replaceFirst("(\\w+)\\s+(\\w+)", "$2 $1");
        String two = "hello girl hi hot".replaceAll("(\\w+)\\s+(\\w+)", "$2 $1");
        System.out.println(one);   // girl hello hi hot
        System.out.println(two);   // girl hello hot hi

        // 重复标点符号替换
        String input = "假如生活欺骗了你，，，相信吧，，，快乐的日子将会来临！！！…………";
        String duplicateSymbolReg = "([。？！?!，]|\\.\\.\\.|……)+";
        input = input.replaceAll(duplicateSymbolReg, "$1");
        System.out.println(input);

    }


}
