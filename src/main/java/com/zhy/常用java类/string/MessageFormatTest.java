package com.zhy.常用java类.string;

import org.junit.Test;

import java.text.MessageFormat;

/**
 * <p> MessageFormatTest </p>
 *
 * @author zhouhongyin
 * @since 2024/1/13 16:57
 */
public class MessageFormatTest {

    @Test
    public void format() {

        String content = "没有通过{0}报名！参赛赛道：{1}，参赛团队：{2}";
        String[] args = new String[]{"111", "222", "333"};

        String format = MessageFormat.format(content, args);
        System.out.println(format);

    }


}
