package com.zhy.java基础.jvm.bytecodeprecess.stackframe;

import org.junit.Test;

/**
 * <p>局部变量表槽复用对垃圾收集的影响</p>
 * <p>
 * jvm option：{@code -verbose:gc}
 *
 * @author zhouhongyin
 * @since 2023/10/22 15:47
 */
public class LocalVariablesTablesTest {

    /**
     * 没有回收掉placeholder所占的内存是能说得过去，因为在执行System.gc()时，
     * 变量placeholder还处于作用域之内，虚拟机自然不敢回收掉placeholder的内存
     */
    @Test
    public void test1() {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        System.gc();
    }

    @Test
    public void test2() {
        byte[] placeholder = new byte[64 * 1024 * 1024];
        placeholder = null;
        System.gc();
    }


    /**
     * 加入了花括号之后，placeholder的作用域被限制在花括号以内，从代码逻辑上讲，在执行
     * System.gc()的时候，placeholder已经不可能再被访问了，但执行这段程序，会发现运行结果如下，还是
     * 有64MB的内存没有被回收掉
     */
    @Test
    public void test3() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        System.gc();

    }

    /**
     * placeholder能否被回收的根本原因就是：局部变量表中的变量槽是否还存有
     * 关于placeholder数组对象的引用。第一次修改中，代码虽然已经离开了placeholder的作用域，但在此之
     * 后，再没有发生过任何对局部变量表的读写操作，placeholder原本所占用的变量槽还没有被其他变量
     * 所复用，所以作为GC Roots一部分的局部变量表仍然保持着对它的关联。这种关联没有被及时打断，
     * 绝大部分情况下影响都很轻微。但如果遇到一个方法，其后面的代码有一些耗时很长的操作，而前面
     * 又定义了占用了大量内存但实际上已经不会再使用的变量，手动将其设置为null值（用来代替那句int
     * a=0，把变量对应的局部变量槽清空）便不见得是一个绝对无意义的操作，这种操作可以作为一种在极
     * 特殊情形（对象占用内存大、此方法的栈帧长时间不能被回收、方法调用次数达不到即时编译器的编
     * 译条件）下的“奇技”来使用
     */
    @Test
    public void test4() {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }


}
