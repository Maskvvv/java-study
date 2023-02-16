package com.zhy.other.toolclass.beanutils;

import com.zhy.other.toolclass.beanutils.bean.Student1;
import com.zhy.other.toolclass.beanutils.bean.Student2;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author zhouhongyin
 * @since 2021/7/26 16:29
 */

public class BeanUtilsTest {
    public static void main(String[] args) {
        Student1 student1 = new Student1("zhy",10);
        Student2 student2 = new Student2();

        BeanUtils.copyProperties(student1,student2);

        System.out.println(student2);
    }
}
