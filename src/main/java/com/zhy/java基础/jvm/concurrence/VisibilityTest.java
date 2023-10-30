package com.zhy.java基础.jvm.concurrence;

/**
 * <p> VisibilityTest </p>
 * <p>
 * 除了volatile之外，Java还有两个关键字能实现可见性，它们是synchronized和final。同步块的可见
 * 性是由“对一个变量执行unlock操作之前，必须先把此变量同步回主内存中（执行store、write操
 * 作）”这条规则获得的。而final关键字的可见性是指：被final修饰的字段在构造器中一旦被初始化完
 * 成，并且构造器没有把“this”的引用传递出去（this引用逃逸是一件很危险的事情，其他线程有可能通
 * 过这个引用访问到“初始化了一半”的对象），那么在其他线程中就能看见final字段的值。
 *
 * @author zhouhongyin
 * @since 2023/10/29 21:46
 */
public class VisibilityTest {

    public static final int i;
    public final int j;

    static {
        i = 0;
        // 省略后续动作
    }

    {
        // 也可以选择在构造函数中初始化
        j = 0;
        // 省略后续动作
    }

}
