package com.zhy.spring.spEL.languagereference;

import com.zhy.spring.spEL.model.Inventor;
import com.zhy.spring.spEL.model.Simple;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhongyin
 * @since 2022/7/8 10:27
 */
public class LanguageReferenceTest implements BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    /**
     * Literal Expressions
     */
    @Test
    public void spELTest1() {
        ExpressionParser parser = new SpelExpressionParser();

        // evals to "Hello World"
        String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();

        double avogadrosNumber = (Double) parser.parseExpression("6.0221415E+23").getValue();

        // evals to 2147483647
        int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();

        boolean trueValue = (Boolean) parser.parseExpression("true").getValue();

        Object nullValue = parser.parseExpression("null").getValue();
    }

    /**
     * Properties
     */
    @Test
    public void spELTest2() {
        ExpressionParser parser = new SpelExpressionParser();

        StandardEvaluationContext context = new StandardEvaluationContext();

        // evals to 1856
        int year = (Integer) parser.parseExpression("birthdate.year + 1900").getValue(context);

        String city = (String) parser.parseExpression("placeOfBirth.city").getValue(context);
    }

    /**
     * arrays
     */
    @Test
    public void spELTest3() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        // Inventions Array
        List<Inventor> inventors = new ArrayList<>();


        // evaluates to "Induction motor"
        String invention = parser.parseExpression("inventors[3]").getValue(
                context, inventors, String.class);

        // Members List

        // evaluates to "Nikola Tesla"
        String name = parser.parseExpression("inventors[0].name").getValue(
                context, inventors, String.class);


        // Officer's Dictionary

        Inventor pupin = parser.parseExpression("officers['president']").getValue(
                context, Inventor.class);

// evaluates to "Idvor"
        String city = parser.parseExpression("officers['president'].placeOfBirth.city").getValue(
                context, String.class);

// setting values
        parser.parseExpression("officers['advisors'][0].placeOfBirth.country").setValue(
                context, "Croatia");

    }

    /**
     * Inline Lists
     */
    @Test
    public void spELTest4() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        // evaluates to a Java list containing the four numbers
        List numbers = (List) parser.parseExpression("{1,2,3,4}").getValue(context);

        List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context);

    }

    /**
     * Inline Maps
     */
    @Test
    public void spELTest5() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        // evaluates to a Java map containing the two entries
        Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue(context);

        Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue(context);
    }

    /**
     *  Array Construction
     */
    @Test
    public void spELTest6() {
        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();

        int[] numbers1 = (int[]) parser.parseExpression("new int[4]").getValue(context);

        // Array with initializer
        int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue(context);

        // Multi dimensional array
        int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue(context);
    }

    /**
     *  Methods
     */
    @Test
    public void spELTest7() {
        ExpressionParser parser = new SpelExpressionParser();

        Simple simple = new Simple();
        EvaluationContext context = new StandardEvaluationContext(simple);


        // string literal, evaluates to "bc"
        String bc = parser.parseExpression("'abc'.substring(1, 3)").getValue(String.class);

        // evaluates to true
        boolean isMember = parser.parseExpression("isMember('Mihajlo Pupin')").getValue(context, Boolean.class);

        System.out.println(isMember);
    }

    /**
     *  Constructors
     */
    @Test
    public void spELTest8() {
        ExpressionParser parser = new SpelExpressionParser();


        Inventor einstein = parser.parseExpression("new com.zhy.spring.spEL.model.Inventor('Albert Einstein', 'German')")
                .getValue(Inventor.class);

        // create new Inventor instance within the add() method of List

        EvaluationContext context = new StandardEvaluationContext();
        Object value = parser.parseExpression("Members.add(new com.zhy.spring.spEL.model.Inventor('Albert Einstein', 'German'))")
                .getValue(context);

        System.out.println();
    }

    /**
     *  Variables
     */
    @Test
    public void spELTest9() {
        ExpressionParser parser = new SpelExpressionParser();

        Inventor tesla = new Inventor("Nikola Tesla", "Serbian");
        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.setVariable("newName", "Mike Tesla");

        parser.parseExpression("name = #newName").getValue(context, tesla);
        System.out.println(tesla.getName());  // "Mike Tesla"

    }

    /**
     *  Functions 1
     */
    @Test
    public void spELTest10() throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();

        Class<Simple> simpleClass = Simple.class;

        Method isMember = simpleClass.getMethod("isName", String.class);
        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("isName", isMember);

        Boolean res = parser.parseExpression("#isName('asdf')").getValue(context, Boolean.class);
        System.out.println(res);





    }

    /**
     *  Functions 1
     */
    @Test
    public void spELTest11() throws NoSuchMethodException {
        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = SimpleEvaluationContext.forReadOnlyDataBinding().build();
        context.setVariable("reverseString",
                StringUtils.class.getDeclaredMethod("reverse", String.class));

        String helloWorldReversed = parser.parseExpression(
                "#reverseString('hello')").getValue(context, String.class);

        System.out.println(helloWorldReversed);

    }

    /**
     *  Bean References
     */
    @Test
    public void spELTest12() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(beanFactory));

        // This will end up calling resolve(context,"something") on MyBeanResolver during evaluation
        Object bean = parser.parseExpression("@something").getValue(context);

    }

    /**
     *   Ternary Operator (If-Then-Else) 1
     */
    @Test
    public void spELTest13() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        String falseString = parser.parseExpression("false ? 'trueExp' : 'falseExp'").getValue(String.class);
        System.out.println(falseString);

    }

    /**
     *  The Elvis Operator
     */
    @Test
    public void spELTest14() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();

        String name = "Elvis Presley";
        String displayName = (name != null ? name : "Unknown");


        // alternatively
        String name1 = parser.parseExpression("name?:'Unknown'").getValue(new Inventor(), String.class);
        System.out.println(name1);  // 'Unknown'

    }

    /**
     *  Collection Selection
     */
    @Test
    public void spELTest15() {
        Inventor inventor = new Inventor();
        List<Inventor> inventors = new ArrayList<>();
        inventor.setInventors(inventors);

        for (int i = 0; i < 10; i++) {
            Inventor iv = new Inventor();
            iv.setName("mike" + i);
            inventors.add(iv);
        }

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(inventor);

        List<Inventor> list = (List<Inventor>) parser.parseExpression("inventors.?[name.equals('mike1')]")
                .getValue(context);

        System.out.println(list);

    }

    /**
     *  Collection Projection
     */
    @Test
    public void spELTest16() {
        Inventor inventor = new Inventor();
        List<Inventor> inventors = new ArrayList<>();
        inventor.setInventors(inventors);

        for (int i = 0; i < 10; i++) {
            Inventor iv = new Inventor();
            iv.setName("mike" + i);
            inventors.add(iv);
        }

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(inventor);

        List<String> list = (List<String>) parser.parseExpression("inventors.![name]")
                .getValue(context);

        System.out.println(list);

    }

    /**
     *  Expression templating (文字 表达式混排)
     */
    @Test
    public void spELTest17() {

        ExpressionParser parser = new SpelExpressionParser();

        // ParseExpression()方法的第二个参数是ParserContext类型。ParserContext接口用于影响表达式的解析方式，以支持表达式模板功能
        String randomPhrase = parser.parseExpression(
                "random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);

        System.out.println(randomPhrase);

    }


    /**
     *  Expression templating (文字 表达式混排)
     */
    @Test
    public void spELTest18() {

        ExpressionParser parser = new SpelExpressionParser();

        EvaluationContext context = SimpleEvaluationContext.forReadWriteDataBinding().build();
        context.setVariable("newName", "Mike Tesla");

        TemplateParserContext templateParserContext = new TemplateParserContext();
        String value = parser.parseExpression("name: #{#newName}", templateParserContext).getValue(context, String.class);
        System.out.println(value);  // "Mike Tesla"


    }



}
