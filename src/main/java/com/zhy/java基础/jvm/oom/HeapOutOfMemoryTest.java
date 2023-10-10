package com.zhy.java基础.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * <p> OOM test </p>
 *
 * JVM Args：{@code -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError}
 *
 * @author zhouhongyin
 * @since 2023/10/10 17:27
 */
public class HeapOutOfMemoryTest {

    static class OOMObject {
    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }
    }



}
