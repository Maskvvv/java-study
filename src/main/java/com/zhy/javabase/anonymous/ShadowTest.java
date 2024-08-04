package com.zhy.javabase.anonymous;

/**
 * <p> 问作用域内的局部变量、定义和访问匿名内部类成员 </p>
 * <p>
 * 匿名内部类与局部类对作用域内的变量拥有相同的的访问权限。
 * <p>
 * (1)、匿名内部类可以访问外部内的所有成员；
 * <p>
 * (2)、匿名内部类不能访问外部类未加final修饰的变量（注意：JDK1.8即使没有用final修饰也可以访问）；
 * <p>
 * (3)、属性屏蔽，与内嵌类相同，匿名内部类定义的类型（如变量）会屏蔽其作用域范围内的其他同名类型（变量）
 *
 * @author zhouhongyin
 * @since 2023/7/21 10:34
 */
public class ShadowTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {
            System.out.println("x = " + x);
            System.out.println("this.x = " + this.x);
            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);
        }
    }

    public static void main(String... args) {
        ShadowTest st = new ShadowTest();
        ShadowTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}
