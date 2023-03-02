package com.zhy.spring.spEL.languagereference;

import com.zhy.spring.spEL.model.Inventor;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author zhouhongyin
 * @since 2023/3/2 13:54
 */
public class OperatorTest {


    /**
     *  Operators
     *
     *  lt (<)
     *
     *  gt (>)
     *
     *  le (<=)
     *
     *  ge (>=)
     *
     *  eq (==)
     *
     *  ne (!=)
     *
     *  div (/)
     *
     *  mod (%)
     *
     *  not (!).
     */
    @Test
    public void spELTest8() {
        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = new StandardEvaluationContext();

        // evaluates to true
        boolean trueValue = parser.parseExpression("2 == 2").getValue(Boolean.class);

        // evaluates to false
        boolean falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);

        // evaluates to true
        boolean trueValue1 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
        System.out.println(trueValue1);

        // evaluates to false
        boolean instanceofValue = parser.parseExpression(
                "'xyz' instanceof T(Integer)").getValue(Boolean.class);

        // evaluates to true
        boolean matchesValue = parser.parseExpression(
                "'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);

        // evaluates to false
        boolean matchesValue1 = parser.parseExpression(
                "'5.0067' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
    }

    /**
     *  Logical Operators
     *  and (&&)
     *
     *  or (||)
     *
     *  not (!)
     */
    @Test
    public void spELTest9() {
        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = new StandardEvaluationContext();


        // evaluates to false
        boolean falseValue = parser.parseExpression("true and false").getValue(Boolean.class);

        // evaluates to true
        String expression = "isMember('Nikola Tesla') and isMember('Mihajlo Pupin')";
        boolean trueValue = parser.parseExpression(expression).getValue(context, Boolean.class);

        // -- OR --

        // evaluates to true
        boolean trueValue1 = parser.parseExpression("true or false").getValue(Boolean.class);

        // evaluates to true
        String expression1 = "isMember('Nikola Tesla') or isMember('Albert Einstein')";
        boolean trueValue2 = parser.parseExpression(expression).getValue(context, Boolean.class);

        // -- NOT --

        // evaluates to false
        boolean nfalseValue = parser.parseExpression("!true").getValue(Boolean.class);

        // -- AND and NOT --
        String expression3 = "isMember('Nikola Tesla') and !isMember('Mihajlo Pupin')";
        Boolean falseValue23 = parser.parseExpression(expression).getValue(context, Boolean.class);
    }

    /**
     *  Mathematical Operators
     */
    @Test
    public void spELTest10() {
        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = new StandardEvaluationContext();


        // Addition
        int two = parser.parseExpression("1 + 1").getValue(Integer.class);  // 2

        String testString = parser.parseExpression(
                "'test' + ' ' + 'string'").getValue(String.class);  // 'test string'

        // Subtraction
        int four = parser.parseExpression("1 - -3").getValue(Integer.class);  // 4

        double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class);  // -9000

        // Multiplication
        int six = parser.parseExpression("-2 * -3").getValue(Integer.class);  // 6

        double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class);  // 24.0

        // Division
        int minusTwo = parser.parseExpression("6 / -3").getValue(Integer.class);  // -2

        double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class);  // 1.0

        // Modulus
        int three = parser.parseExpression("7 % 4").getValue(Integer.class);  // 3

        int one1 = parser.parseExpression("8 / 5 % 2").getValue(Integer.class);  // 1

        // Operator precedence
        int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class);  // -21
    }

    /**
     *  The Assignment Operator
     */
    @Test
    public void spELTest11() {
        ExpressionParser parser = new SpelExpressionParser();

        Inventor inventor = new Inventor();

        //EvaluationContext context = new StandardEvaluationContext(inventor);
        //parser.parseExpression("name").setValue(context, "Aleksandar Seovic");

        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        parser.parseExpression("name").setValue(context, inventor, "Aleksandar Seovic");

        // alternatively
        String aleks = parser.parseExpression("name = 'Aleksandar Seovic'").getValue(context, inventor, String.class);

        System.out.println(inventor);
    }

}
