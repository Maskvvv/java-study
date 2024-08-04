package com.zhy.javabase.collection.set;

import org.junit.Test;

import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 线程安全 Set
 *
 * @author zhouhongyin
 * @since 2023/2/16 15:39
 */
public class SetTest {

    /**
     * CopyOnWriteArraySet: 无序
     *
     * ConcurrentSkipListSet：有序
     *
     */
    @Test
    public void ConcurrentSet() {

        CopyOnWriteArraySet<String> copyOnWriteArraySet = new CopyOnWriteArraySet<>();

        ConcurrentSkipListSet<String> skipListSet = new ConcurrentSkipListSet<>();
    }

}
