package com.zhy.spring.aop.lock;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 10:52
 */
public interface MyLockProcessor {

    void lock(String key);

    void lock(String key, long leaseTime) throws Exception;

    void unlock(String key);

}
