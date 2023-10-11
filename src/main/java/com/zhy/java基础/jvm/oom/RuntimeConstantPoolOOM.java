package com.zhy.java基础.jvm.oom;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * <p> ConstantPool </p>
 *
 * <pre>
 *     使用JDK 7或更高版本的JDK来运行这段程序并不会得到相同的结果，无论是在JDK 7中继续使
 * 用-XX：MaxPermSize参数或者在JDK 8及以上版本使用-XX：MaxMeta-spaceSize参数把方法区容量同
 * 样限制在6MB，也都不会重现JDK 6中的溢出异常，循环将一直进行下去，永不停歇[1]。出现这种变
 * 化，是因为自JDK 7起，原本存放在永久代的字符串常量池被移至Java堆之中，所以在JDK 7及以上版
 * 本，限制方法区的容量对该测试用例来说是毫无意义的。这时候使用-Xmx参数限制最大堆到6MB就能
 * 够看到以下两种运行结果之一
 * </pre>
 * <p>
 * JDK6: {@code VM Args：-XX:PermSize=6M -XX:MaxPermSize=6M}
 * JDK7+: {@code VM Args：-Xmx6M}
 *
 * @author zhouhongyin
 */
public class RuntimeConstantPoolOOM {
    /**
     * StringConstantPool OOM
     */
    public void test1() {
        // 使用Set保持着常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        // 在short范围内足以让6MB的PermSize产生OOM了
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }

    /**
     * StringConstantPool
     */
    @Test
    public void test2() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

    }

}
