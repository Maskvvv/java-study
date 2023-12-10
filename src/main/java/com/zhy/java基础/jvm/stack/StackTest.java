package com.zhy.java基础.jvm.stack;

/**
 * 除了 static private 构造方法 等能够唯一确定一个方法的情况外，
 * 其他可通过继承修改其方法实现的方法，其字节码指令对于的都是 invokeDynamic
 *
 * @author zhouhongyin
 * @since 2023/12/10 18:59
 */
public class StackTest extends A {

    public static A a;

    public static void main(String[] args) {

        a = new StackTest();
        a.funcA();

        StackTest b = new StackTest();
        b.funcA();

        b.funcB();

        b.funcC();
    }


    @Override
    public int funcA() {
        return 0;
    }

    public int funcB() {


        return 0;
    }

    private int funcC() {
        return 0;
    }


}

abstract class A {

    public abstract int funcA();

}
