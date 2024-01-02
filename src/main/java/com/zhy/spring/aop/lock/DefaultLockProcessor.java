package com.zhy.spring.aop.lock;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 10:59
 */
@Service
public class DefaultLockProcessor implements MyLockProcessor {

    private static final Map<String, ReentrantLock> lockMap = new HashMap<>();

    // TODO-zhouhy 2023/12/21 lock expired
    // TODO-zhouhy 2023/12/21 lock remove

    @Override
    public void lock(String key) {
        getLock(key).lock();
    }

    @Override
    public void lock(String key, long leaseTime) throws Exception {
        getLock(key).lock();
    }



    @Override
    public void unlock(String key) {
        ReentrantLock lock = getLock(key);
        lock.unlock();
    }


    private ReentrantLock getLock(String key) {
        ReentrantLock lock = lockMap.get(key);

        if (lock == null) {
            synchronized (key.intern()) {
                lock = lockMap.get(key);
                if (lock != null) return lock;

                lock = new ReentrantLock();
                lockMap.put(key, lock);
            }
        }

        return lock;
    }
}
