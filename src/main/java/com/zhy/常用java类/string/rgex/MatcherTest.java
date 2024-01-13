package com.zhy.常用java类.string.rgex;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
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
    public void baseMethod() {
        // 这个正则表达式有两个组，
        // group(0) 是 \\$\\{([^{}]+?)\\}
        // group(1) 是 ([^{}]+?)
        String regex = "\\$\\{([^{}]+?)\\}";
        Pattern pattern = Pattern.compile(regex);
        String input = "${name}-babalala-${age}-${address}";

        Matcher matcher = pattern.matcher(input);
        System.out.println(matcher.groupCount());
        // find() 像迭代器那样向前遍历输入字符串
        while (matcher.find()) {
            System.out.println(matcher.group(0) + ", pos: "
                    + matcher.start() + "-" + (matcher.end() - 1));
            System.out.println(matcher.group(1) + ", pos: " +
                    matcher.start(1) + "-" + (matcher.end(1) - 1));
        }

    }

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
    public void groupPlaceholder() {
        String content = "没有通过{0}报名！参赛赛道：{1}，参赛团队：{2}";
        List<String> args = Arrays.asList("111", "222", "333");


        Matcher matcher = Pattern.compile("\\{(\\d+?)\\}").matcher(content);
        while (matcher.find()) {
            int index = Integer.parseInt(matcher.group(1));
            System.out.println(index);





            //if (index < 0 || index >= args.size()) {
            //    matcher.replaceFirst("null");
            //} else {
            //    matcher.replaceFirst(args.get(index));
            //}

        }
        System.out.println(matcher.toString());
    }

    @Test
    public void groupReplace() {
        String s = "12345678";
        Pattern pattern = Pattern.compile("(\\d)(?=(\\d{3})+$)");
        System.out.println(pattern.matcher(s).replaceAll("$1,"));
    }

    @Test
    public void repeatFileName() {
        String finalPrefixFileName = "报名信息_20231026";
        String format = String.format("(?<=%s) \\((\\d)\\).*", finalPrefixFileName);
        //Pattern pattern = Pattern.compile("(?<=报名信息_20231026)\\((\\d)\\).*");
        Pattern pattern = Pattern.compile(format);


        File file = new File("D:\\UserFiles\\桌面");

        String[] list = file.list();
        System.out.println(Arrays.toString(list));

        for (String name : list) {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
            }
        }


        String[] list1 = file.list((f, name) -> {
            Matcher matcher = pattern.matcher(name);
            if (matcher.find()) {
                System.out.println(matcher.group(1));
                return true;
            } else {
                return false;
            }
        });
        System.out.println(Arrays.toString(list1));


    }

}
