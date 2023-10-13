package com.zhy.java基础.jvm.gc;

/**
 * <p> 大对象直接进入老年代 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/13 11:37
 */
public class BigObjectToOld {

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

    private static final int _1MB = 1024 * 1024;
    /**
     *
     * <p>
     *     JVM: {@code -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *     -XX:PretenureSizeThreshold=3145728 -XX:+UseParNewGC}
     * </p>
     * <br/>
     *  -XX：PretenureSizeThreshold参数只对Serial和ParNew两款新生代收集器有效，HotSpot
     * 的其他新生代收集器，如Parallel Scavenge并不支持这个参数。如果必须使用此参数进行调优，可考虑
     * ParNew加CMS的收集器组合。
     *
     *
     *
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; //直接分配在老年代中
    }

}
