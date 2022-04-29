package com.zhy.常用java类.time;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author zhouhongyin
 * @since 2021/7/23 16:30
 */

public class CalendarTest {
    public static void main(String[] args) {
        int july = Calendar.JULY;
        Calendar instance = Calendar.getInstance();

        Date date = DateUtils.addDays(new Date(1654099199000L), 14);
        System.out.println(date.getTime());

        Date date1 = DateUtils.addDays(new Date(1651334400000L), 14);
        System.out.println(date1);
        System.out.println(date1.getTime());
    }
}
