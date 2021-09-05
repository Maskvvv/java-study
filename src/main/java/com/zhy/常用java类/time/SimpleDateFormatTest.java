package com.zhy.常用java类.time;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/30 15:23
 */
public class SimpleDateFormatTest {
    public static void main(String[] args) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        System.out.println(format);
    }
}
