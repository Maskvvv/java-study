package com.zhy.java基础.jvm.constantspool;

/**
 * <p> Constants pool </p>
 *
 * @author zhouhongyin
 * @since 2023/10/18 13:46
 */
public class TestClass {
    private int m;

    public int inc(String s) {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }

    }
}
