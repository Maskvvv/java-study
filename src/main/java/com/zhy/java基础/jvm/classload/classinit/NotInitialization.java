package com.zhy.java基础.jvm.classload.classinit;

/**
 * <p> 非主动使用类字段演示 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/21 17:02
 */
public class NotInitialization {
    static {
        i = 0; // 给变量复制可以正常编译通过

        // 这句编译器会提示“非法向前引用”
        //System.out.print(i);
    }

    static int i = 1;


    public static void main(String[] args) {
        // 下面两种情况不会出发类的初始化过程

        // 1.通过子类引用父类的静态字段，不会导致子类初始化
        System.out.println(SubClass.value);

        // 2.通过数组定义来引用类，不会触发此类的初始化
        SuperClass[] sca = new SuperClass[10];

        // 3.常量在编译阶段会存入调用类的常量池中，本质上没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
        System.out.println(ConstClass.HELLOWORLD);

    }
}
