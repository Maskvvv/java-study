package com.zhy.常用java类.time;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date(1654099199000L));
        System.out.println(calendar.get(Calendar.YEAR));
        calendar.get(Calendar.MONTH);					//获取月份
        calendar.get(Calendar.DATE);					//获取日

        calendar.get(Calendar.HOUR);					//时（12小时制）
        calendar.get(Calendar.HOUR_OF_DAY);				//时（24小时制）
        calendar.get(Calendar.MINUTE);					//分
        calendar.get(Calendar.SECOND);					//秒
        calendar.get(Calendar.DAY_OF_WEEK);				//一周的第几天


        Date date = DateUtils.addDays(new Date(1654099199000L), 14);
        System.out.println(date.getTime());

        Date date1 = DateUtils.addDays(new Date(1651334400000L), 14);
        System.out.println(date1);
        System.out.println(date1.getTime());
    }

    @Test
    public void test1() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(1640966400000L));


        System.out.println(calendar.get(Calendar.YEAR));
    }


    @Test
    public void test2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        //修改日历的指定时间
        calendar.setTime(sdf.parse("2018-09-01"));
        System.out.println(calendar.getTime());
        System.out.println(calendar.get(Calendar.YEAR));


        calendar.add(Calendar.YEAR, 4);

        System.out.println(sdf.format(calendar.getTime()));


        Object da = "2020.0";

        System.out.println( Double.valueOf((String) da).intValue());
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());

        System.out.println(calendar.get(Calendar.YEAR));
        calendar.add(Calendar.YEAR, -20);
        System.out.println(calendar.get(Calendar.YEAR));

        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);



        System.out.println(sdf.format(calendar.getTime()));
        System.out.println(calendar.getTimeInMillis());


    }


}
