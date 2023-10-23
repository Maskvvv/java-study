package com.zhy.java基础.jvm.bytecodeprecess.dipatch;

/**
 * <p> 方法动态分派演示 </p>
 * <p>
 * 动态分派的实现过程，它与Java语言多态性的另外
 * 一个重要体现——重写（Override）有着很密切的关联
 *
 * @author zhouhongyin
 * @since 2023/10/22 17:10
 */
public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
