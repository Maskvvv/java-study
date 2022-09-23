package com.zhy.常用java类.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author zhouhongyin
 * @since 2021/7/23 16:28
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


        Date date1 = new Date(1567267200000L);
        System.out.println(date1.getYear());
        System.out.println(date1);
    }


}
