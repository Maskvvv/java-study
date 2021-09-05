package com.zhy.java基础.继承;

import org.junit.Test;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/8/25 15:02
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
