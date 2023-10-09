package com.zhy.常用java类.string.rgex;

import org.junit.jupiter.api.Test;

/**
 * <p> Java regular expression test </p>
 *
 * @author zhouhongyin
 * @since 2023/10/7 16:06
 */
public class RgexTest {

    /**
     * 千分位分隔符
     */
    @Test
    public void theThousandthSeparator() {
        String phoneNumber = "123456789";
        // (\d)(?=((\d{3})+(\D*))$)
        System.out.println(phoneNumber.replaceAll("(\\d)(?=(\\d{3})+$)", "$1,"));
    }

    /**
     * 脱敏
     */
    @Test
    public void hyposensitization() {
        // 脱敏
        String tel = "18304072984";
        // 括号表示组，被替换的部分$n表示第n组的内容
        tel = tel.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println(tel);   // output: 183****2984

    }

    /**
     * 交换单词
     */
    @Test
    public void swapWords() {
        // 交换单词
        String one = "hello girl hi hot".replaceFirst("(\\w+)\\s+(\\w+)", "$2 $1");
        String two = "hello girl hi hot".replaceAll("(\\w+)\\s+(\\w+)", "$2 $1");
        System.out.println(one);   // girl hello hi hot
        System.out.println(two);   // girl hello hot hi
    }

    /**
     * 重复标点符号替换
     */
    @Test
    public void repeatWords() {
        // 重复标点符号替换
        String input = "假如生活欺骗了你，，，相信吧，，，快乐的日子将会来临！！！…………";
        String duplicateSymbolReg = "([。？！?!，]|\\.\\.\\.|……)+";
        input = input.replaceAll(duplicateSymbolReg, "$1");
        System.out.println(input);
    }

    /**
     * 下划线转驼峰
     */
    @Test
    public void underLineToDump() {

        // 重复标点符号替换
        String input = "user_name";
        String duplicateSymbolReg = "(?:_)(\\w)";
        String dump = input.replaceAll(duplicateSymbolReg, "$1");
        System.out.println(dump);
    }


}
