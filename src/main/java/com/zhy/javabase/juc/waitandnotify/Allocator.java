package com.zhy.javabase.juc.waitandnotify;

import java.util.List;

/**
 * wait 和 notify 实现多资源申请
 *
 * @author zhouhongyin
 * @since 2023/2/15 15:51
 */

class Allocator {
    private List<Object> als;

    // 一次性申请所有资源
    synchronized void apply(Object from, Object to) {
        // 经典写法
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        als.add(from);
        als.add(to);
    }

    // 归还资源
    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }
}
