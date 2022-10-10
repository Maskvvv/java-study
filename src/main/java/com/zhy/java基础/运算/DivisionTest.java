package com.zhy.java基础.运算;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zhouhongyin
 * @since 2022/10/10 10:02
 */
public class DivisionTest {

    @Test
    public void divisionTest() {
        double a = 2.6893;
        double b = 2.9017;

        System.out.println(a / b);
        System.out.println(BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b), 4));


    }



}
