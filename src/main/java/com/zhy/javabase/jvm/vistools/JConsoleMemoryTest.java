package com.zhy.javabase.jvm.vistools;

import java.util.ArrayList;
import java.util.List;

/**
 * JConsole Memory
 *
 * @author zhouhongyin
 * @since 2023/10/14 18:18
 */
public class JConsoleMemoryTest {

    /**
     * 内存占位符对象，一个OOMObject大约占64KB
     * <p>
     * JVM Option: -Xms100m -Xmx100m -XX:+UseSerialGC
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<OOMObject>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(500);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }

}
