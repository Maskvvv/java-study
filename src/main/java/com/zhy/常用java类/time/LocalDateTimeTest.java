package com.zhy.常用java类.time;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 *
 * @author zhouhongyin
 * @since 2021/7/23 16:28
 */

public class LocalDateTimeTest {

    @Test
    public void t1() {
        //        System.out.println(System.currentTimeMillis());
//        Date date = new Date(System.currentTimeMillis());
//        System.out.println(date);
        LocalDateTime now = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        System.out.println(now);
        System.out.println(date);
        System.out.println(LocalTime.now());


        Date date1 = new Date(1640966400000L);
        System.out.println(date1.getYear());
        System.out.println(date1);
    }

    @Test
    public void t2() {

        System.out.println(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));

    }


}
