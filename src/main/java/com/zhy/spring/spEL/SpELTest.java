package com.zhy.spring.spEL;

import com.zhy.spring.model.Order;
import com.zhy.spring.spEL.model.Demo;
import com.zhy.spring.spEL.model.Inventor;
import com.zhy.spring.spEL.model.Simple;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.GregorianCalendar;

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

    @Test
    public void spELTest2() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'");
        String message = (String) exp.getValue();
    }

    /**
     * calling method
     */
    @Test
    public void spELTest3() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println(message);
    }

    /**
     * calling constructor
     */
    @Test
    public void spELTest4() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("new String('hello world').toUpperCase()");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

    /**
     * retrieve properties
     */
    @Test
    public void spELTest5() {
        // Create and set a calendar
        GregorianCalendar c = new GregorianCalendar();
        c.set(1856, 7, 9);

        // The constructor arguments are name, birthday, and nationality.
        Inventor tesla = new Inventor("Nikola Tesla", c.getTime(), "Serbian");

        ExpressionParser parser = new SpelExpressionParser();

        Expression exp = parser.parseExpression("name1"); // Parse name as an expression
        String name = (String) exp.getValue(tesla);
        // name == "Nikola Tesla"
        System.out.println(name);


        exp = parser.parseExpression("name == 'Nikola Tesla'");
        boolean result = exp.getValue(tesla, Boolean.class);
        // result == true
        System.out.println(result);

    }

    /**
     * Type Conversion
     */
    @Test
    public void spELTest6() {
        Simple simple = new Simple();
        simple.booleanList.add(true);

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        ExpressionParser parser = new SpelExpressionParser();

        // "false" is passed in here as a String. SpEL and the conversion service
        // will recognize that it needs to be a Boolean and convert it accordingly.
        parser.parseExpression("booleanList[0]").setValue(context, simple, "false");

        // b is false
        Boolean b = simple.booleanList.get(0);

        System.out.println(b);
    }

    /**
     * Parser Configuration
     */
    @Test
    public void spELTest7() {
        // Turn on:
        // - auto null reference initialization
        // - auto collection growing
        SpelParserConfiguration config = new SpelParserConfiguration(true, true);

        ExpressionParser parser = new SpelExpressionParser(config);

        Expression expression = parser.parseExpression("list[3]");

        Demo demo = new Demo();

        Object o = expression.getValue(demo);

        // demo.list will now be a real collection of 4 entries
        // Each entry is a new empty String
        System.out.println(o);
    }

    /**
     * Parser bean
     */
    @Test
    public void spELTest8() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        Inventor bean = new Inventor();
        bean.setName("my-bean");
        factory.registerSingleton("inventor", bean);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(factory));


        Inventor o = parser.parseExpression("@inventor").getValue(context, Inventor.class);
        String s = parser.parseExpression("@inventor.getName()").getValue(context, String.class);

        System.out.println(o);
        System.out.println(s);
    }


}
