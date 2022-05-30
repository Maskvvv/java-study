package com.zhy.常用java类.time.random;

import com.zhy.layui.entity.TreeNode;
import org.apache.commons.collections4.list.TransformedList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zhouhongyin
 * @since 2022/5/6 17:07
 */
public class RandomTest {

    public static void main(String[] args) {

        int left = 1;
        int right = 3;

        Random random = new Random(System.currentTimeMillis());

        // [left, right]
        System.out.println(left + 1 + random.nextInt(right - left));

        System.out.println(left + 1 + (int) (Math.random() * right - left));

        int a = 'a';
        System.out.println(a);

        int A = 'A';
        System.out.println(A);

    }
}
