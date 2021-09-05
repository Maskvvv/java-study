package com.zhy.常用java类.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/7/23 16:28
 */

public class LocalDateTimeTest {
    public static void main(String[] args) {
        //        System.out.println(System.currentTimeMillis());
//        Date date = new Date(System.currentTimeMillis());
//        System.out.println(date);
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(LocalTime.now());

        Date date1 = new Date(20205959919L);

        System.out.println(date1);
    }


}
