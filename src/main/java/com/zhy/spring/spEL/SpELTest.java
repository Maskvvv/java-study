package com.zhy.spring.spEL;

import com.zhy.spring.model.Order;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * @author zhouhongyin
 * @since 2022/7/8 10:27
 */
public class SpELTest {

    @Test
    public void spELTest1() {
        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("#root.purchaseName");
        Order order = new Order();
        order.setPurchaseName("张三");
        System.out.println(expression.getValue(order));
    }

}
