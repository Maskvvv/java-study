package com.zhy.java基础.jvm.oom;

/**
 * <p> Stack deep Overflow test </p>
 *
 * {@code JVM Args：-Xss128k}
 *
 * @author zhouhongyin
 * @since 2023/10/10 17:45
 */
public class JavaVMStackDeepSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {
        JavaVMStackDeepSOF oom = new JavaVMStackDeepSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
