package com.zhy.javabase.jvm.concurrence;

/**
 * <p> 双锁检测（Double Check Lock，DCL）单例模式 </p>
 *
 * @author zhouhongyin
 * @since 2023/10/29 21:21
 */
public class Singleton {
    // volatile 避免指令重排导致空指针
    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
