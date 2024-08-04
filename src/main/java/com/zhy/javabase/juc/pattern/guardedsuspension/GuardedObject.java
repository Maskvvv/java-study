package com.zhy.javabase.juc.pattern.guardedsuspension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * Guarded Suspension 模式：所谓 Guarded Suspension，直译过来就是“保护性地暂停
 *
 * Guarded Suspension 模式本质上是一种等待唤醒机制的实现，只不过 Guarded Suspension 模式将其规范化了。
 * 规范化的好处是你无需重头思考如何实现，也无需担心实现程序的可理解性问题，同时也能避免一不小心写出个 Bug 来。
 * 但 Guarded Suspension 模式在解决实际问题的时候，往往还是需要扩展的，扩展的方式有很多，本
 * 篇文章就直接对 GuardedObject 的功能进行了增强，Dubbo 中 DefaultFuture 这个类也是采用的这种方式
 *
 * Guarded Suspension 模式也常被称作 Guarded Wait 模式、Spin Lock 模式（因为使用了 while 循环去等待），
 * 这些名字都很形象，不过它还有一个更形象的非官方名字：多线程版本的 if。单线程场景中，if 语句是不需要等待的，因为在只有一个线程的条件下，
 * 如果这个线程被阻塞，那就没有其他活动线程了，这意味着 if 判断条件的结果也不会发生变化了。但是多线程场景中，等待就变得有意义了，
 * 这种场景下，if 判断条件的结果是可能发生变化的。所以，用“多线程版本的 if”来理解这个模式会更简单。
 *
 * @author zhouhongyin
 * @since 2023/2/20 13:54
 */
class GuardedObject<T> {
    // 受保护的对象
    T obj;
    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;

    // 保存所有GuardedObject，用来在不同线程中共享对象
    final static Map<Object, GuardedObject> gos = new ConcurrentHashMap<>();

    // 静态方法创建GuardedObject
    static <K> GuardedObject create(K key) {
        GuardedObject go = new GuardedObject();
        gos.put(key, go);
        return go;
    }

    // 唤醒等待的线程
    static <K, T> void fireEvent(K key, T obj) {
        GuardedObject go = gos.remove(key);
        if (go != null) {
            go.onChanged(obj);
        }
    }

    // 获取受保护对象
    T get(Predicate<T> p) {
        lock.lock();
        try {
            //MESA管程推荐写法
            while (!p.test(obj)) {
                done.await(timeout, TimeUnit.SECONDS);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        // 返回非空的受保护对象
        return obj;
    }

    //事件通知方法
    void onChanged(T obj) {
        lock.lock();
        try {
            this.obj = obj;
            done.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
