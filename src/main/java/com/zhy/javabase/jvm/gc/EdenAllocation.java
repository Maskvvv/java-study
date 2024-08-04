package com.zhy.javabase.jvm.gc;

/**
 * <p> 对象优先在 Eden 分配 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/13 11:29
 */
public class EdenAllocation {

    public static void main(String[] args) {
        testAllocation();
    }

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; // 出现一次Minor GC
    }

}
