package com.zhy.常用java类.integer;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p> BigDecimal Test </p>
 *
 * @author zhouhongyin
 * @since 2023/12/14 9:48
 */
public class BigDecimalTest {


    @Test
    public void divide() {

        BigDecimal decimal1 = new BigDecimal(10);
        BigDecimal decimal2 = new BigDecimal(3);
        double res1 = decimal1.divide(decimal2, 2, RoundingMode.HALF_UP).doubleValue();

        System.out.println(res1);

        System.out.println(decimal1.divide(decimal2, 2, RoundingMode.HALF_UP).toString());

    }

}
