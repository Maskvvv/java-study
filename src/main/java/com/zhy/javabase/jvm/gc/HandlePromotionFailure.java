package com.zhy.javabase.jvm.gc;

/**
 * <p> 空间分配担保 </p>
 *  JVM Version -jdk6
 * <p>
 * 在发生Minor GC之前，虚拟机必须先检查老年代最大可用的连续空间是否大于新生代所有对象总空间，如果这个条件成立，那这一次Minor GC可以确保是安全的。
 * 如果不成立，则虚拟机会先查看 {@code -XX:HandlePromotionFailure} 参数的设置值是否允许担保失败（Handle Promotion Failure）；
 * 如果允许，那会继续检查老年代最大可用的连续空间是否大于历次晋升到老年代对象的平均大小，如果大于，将尝试进行一次Minor GC，尽管这次Minor GC是有风险的；
 * 如果小于，或者 -XX:HandlePromotionFailure设置不允许冒险，那这时就要改为进行一次Full GC。
 * </p>
 * @author zhouhongyin
 * @since 2023/10/13 15:51
 */
public class HandlePromotionFailure {

    public static void main(String[] args) {
        testHandlePromotion();
    }

    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
     */
    @SuppressWarnings("unused")
    public static void testHandlePromotion() {
        byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation1 = null;
        allocation4 = new byte[2 * _1MB];
        allocation5 = new byte[2 * _1MB];
        allocation6 = new byte[2 * _1MB];
        allocation4 = null;
        allocation5 = null;
        allocation6 = null;
        allocation7 = new byte[2 * _1MB];
    }
}
