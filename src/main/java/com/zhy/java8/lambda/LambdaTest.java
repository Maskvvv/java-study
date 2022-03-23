package com.zhy.java8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *  
 * @author zhouhongyin
 * @since 2021/8/10 14:56
 */

public class LambdaTest {

    @Test
    public void LambdaTest1(){
        System.out.println("普通实现");
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(names);

        System.out.println("Lambda表达式");
        List<String> names1 = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names1, (o1, o2) -> o1.compareTo(o2));
        System.out.println(names1);

        System.out.println("方法引用");
        List<String> names2 = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names2, String::compareTo);
        System.out.println(names2);
    }

}
