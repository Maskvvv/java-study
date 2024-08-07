package com.zhy.javabase.jvm.gc;

/**
 * <p> 动态对象年龄判定 </p>
 *
 * <p>
 * 为了能更好地适应不同程序的内存状况，HotSpot 虚拟机并不是永远要求对象的年龄必须达到 {@code -XX：MaxTenuringThreshold} 才能晋升老年代，
 * 如果在Survivor空间中相同年龄所有对象大小的总和大于 Survivor 空间的一半，年龄大于或等于该年龄的对象就可以直接进入老年代，
 * 无须等到 {@code -XX:MaxTenuringThreshold} 中要求的年龄。
 * </p>
 *
 * @author zhouhongyin
 * @since 2023/10/13 11:53
 */
public class DynamicTenuring {

    public static void main(String[] args) {
        testTenuringThreshold2();
    }


    private static final int _1MB = 1024 * 1024;
    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     -XX:MaxTenuringThreshold=15
     * -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold2() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4]; // allocation1+allocation2大于survivo空间一半
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }

}
