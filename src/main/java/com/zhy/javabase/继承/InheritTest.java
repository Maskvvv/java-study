package com.zhy.javabase.继承;

import org.junit.Test;

/**
 *
 * @author zhouhongyin
 * @since 2021/8/25 15:02
 */
public class InheritTest {

    @Test
    public void inheritTest1(){
        NcdHealthControllerBaseParam param = new PageOneListRecipeParam("1","2");
        param.setDataSource("3");


        System.out.println(param.getDataSource());
        System.out.println(param);
    }

}
