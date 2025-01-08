package com.zhy.常用java类.time;


import org.apache.commons.lang3.time.FastDateFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class DateTimeUtils {

    private static final Map<String, SimpleDateFormat> DATE_FORMAT_MAP = new HashMap<>();

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    public static String translate(Date date, String format, Locale locale) {

        if (null == date) {
            throw new IllegalArgumentException("date is null");
        }
        if (null == format) {
            throw new IllegalArgumentException("format is null");
        }
        if (null == locale) {
            locale = DEFAULT_LOCALE;
        }

        SimpleDateFormat toFormat;
        if (DATE_FORMAT_MAP.containsKey(format)) {
            toFormat = DATE_FORMAT_MAP.get(format);
        } else {
            toFormat = new SimpleDateFormat(format, locale);
            DATE_FORMAT_MAP.put(format, toFormat);
        }
        return toFormat.format(date);
    }

    public static String translate(Date date, DateTimeFormat format) {
        return translate(date, format.getFormat(), format.getLocale());
    }

    public enum DateTimeFormat {

        DATE_CN_FORMAT("yyyy年MM月dd日", Locale.CHINA),
        DATE_TIME_CN_FORMAT("yyyy年MM月dd日 HH:mm:ss", Locale.CHINA),

        DATE_NORMAL_FORMAT("yyyy-MM-dd", Locale.ENGLISH),
        DATE_TIME_NORMAL_FORMAT("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH),

        DATE_EN_FORMAT("dd MMM yy", Locale.ENGLISH),
        DATE_EN_FORMAT_2("dd MMM yyyy", Locale.ENGLISH),
        DATE_EN_FORMAT_3("dd MMM yyyy", Locale.CHINA),

        ;

        private final String format;

        private final Locale locale;

        DateTimeFormat(String format, Locale locale) {
            this.format = format;
            this.locale = locale;
        }

        public String getFormat() {
            return format;
        }

        public Locale getLocale() {
            return locale;
        }
    }


    public static void main(String[] args) {
        System.out.println(DateTimeUtils.translate(new Date(), DateTimeFormat.DATE_EN_FORMAT));
        System.out.println(DateTimeUtils.translate(new Date(), DateTimeFormat.DATE_CN_FORMAT));
        System.out.println(DateTimeUtils.translate(new Date(), DateTimeFormat.DATE_EN_FORMAT_3));

        System.out.println(FastDateFormat.getInstance("dd MMM yyyy").format(new Date()));
        System.out.println(FastDateFormat.getInstance("dd MMM yyyy", Locale.ENGLISH).format(new Date()));
        System.out.println(FastDateFormat.getInstance("EEE dd MMM yyyy·HH:mm", Locale.ENGLISH).format(new Date()));
    }
}
