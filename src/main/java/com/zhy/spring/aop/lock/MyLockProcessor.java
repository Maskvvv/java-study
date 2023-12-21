package com.zhy.spring.aop.lock;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 10:52
 */
public interface MyLockProcessor {

    void lock(String key);

    boolean tryLock(String key, long timeout) throws Exception;

    void unlock(String key);

}
