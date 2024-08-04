package com.zhy.javabase.exception_;

/**
 * <p> try finally test </p>
 *
 * @author zhouhongyin
 * @since 2023/10/31 14:18
 */
public class TryFinallyTest {


    public static int test(int count) {

        try {
            System.out.println("try");

            for (int i = 0; i < count; i++) {
                if (count < 10) continue;

                return 1;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println("finally");
        }

        throw new RuntimeException("新增作品失败，存在重复的作品code");
    }


    public static void main(String[] args) {
        System.out.println(test(11));
        System.out.println("main");
    }

}
