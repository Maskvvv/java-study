package com.zhy.常用java类.string.format;

import org.apache.commons.lang.text.StrSubstitutor;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/13 17:09
 */
public class StringSubstitutorTest {

    @Test
    public void format() {

        Map<String, String> map = new HashMap<>();
        map.put("code","123321");
        String str1 = "您正在登录${product},验证码为：${code}，五分钟内有效，如非本人操作请忽略。";


        StrSubstitutor sb = new StrSubstitutor(map);
        System.out.println(sb.getEscapeChar());
        String content = sb.replace(str1);
        System.out.println(content);

    }


}
