package com.zhy.java基础.jvm.bytecodeprecess.dipatch;

/**
 * <p> 方法静态分派演示 </p>
 * <p>
 * 和方法的重载有关
 *
 * @author zhouhongyin
 * @since 2023/10/22 16:48
 */
public class StaticDispatch {
    static abstract class Human {
    }

    static class Man extends Human {
    }

    static class Woman extends Human {
    }

    public void sayHello(Human guy) {
        System.out.println("hello,guy!");
    }

    public void sayHello(Man guy) {
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman guy) {
        System.out.println("hello,lady!");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }

    /*

    // 实际类型变化
    Human human = (new Random()).nextBoolean() ? new Man() : new Woman();
    // 静态类型变化
    sr.sayHello((Man)human);
    sr.sayHello((Woman)human);

    */

}
