package com.zhy.javabase.jvm.classload;

/**
 * <p> class init </p>
 *
 * @author zhouhongyin
 * @since 2023/10/31 22:58
 */
public class Singleton {
    private Singleton() {
    }

    private static class LazyHolder {
        static final Singleton INSTANCE = new Singleton();

        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static Object getInstance(boolean flag) {
        if (flag) return new LazyHolder[2];
        return LazyHolder.INSTANCE;
    }

    /**
     * 1.虚拟机必须知道（加载）有这个类，才能创建这个类的数组（容器），
     * 但是这个类并没有被使用到（没有达到初始化的条件），所以不会初始化。
     * <p>
     * 2.新建数组的时候并不是要使用这个类（只是定义了放这个类的容器），
     * 所以不会被链接，调用getInstance(false)的时候约等于告诉虚拟机，
     * 我要使用这个类了，你把这个类造好（链接），然后把static修饰的字符赋予变量（初始化）。
     */
    public static void main(String[] args) {
        getInstance(true);
        System.out.println("----");
        getInstance(false);
    }
}
