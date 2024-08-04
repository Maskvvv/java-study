package com.zhy.javabase.jvm.classload.classinit;

/**
 * <p>{@code <clinit>()}方法执行顺序</p>
 *
 * <p>·<clinit>()方法与类的构造函数（即在虚拟机视角中的实例构造器<init>()方法）不同，它不需要显
 * 式地调用父类构造器，Java虚拟机会保证在子类的<clinit>()方法执行前，父类的<clinit>()方法已经执行
 * 完毕。因此在Java虚拟机中第一个被执行的<clinit>()方法的类型肯定是java.lang.Object。
 * ·由于父类的<clinit>()方法先执行，也就意味着父类中定义的静态语句块要优先于子类的变量赋值
 * 操作，如代码清单7-6中，字段B的值将会是2而不是1。</p>
 *
 * @author zhouhongyin
 * @since 2023/10/21 20:47
 */
public class ClinitProcessOrderTest {


    static class Parent {
        public static int A = 1;

        static {
            A = 2;
        }
    }

    static class Sub extends Parent {
        public static int B = A;
    }

}
