package com.zhy.javabase.jvm.bytecodeprecess.methodinvoke;

/**
 * <p> 方法静态解析演示 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/22 16:39
 */
public class StaticResolution {
    public static void sayHelloStatic() {
        System.out.println("hello world");
    }

    public void sayHelloVirtual() {
        System.out.println("hello world");
    }


    public static void main(String[] args) {
        StaticResolution.sayHelloStatic();
    }
}
