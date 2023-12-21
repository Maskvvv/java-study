package com.zhy.spring.aop.lock;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/12/21 11:12
 */
public interface KeyConvert {
    String getKey(Object... param);
}
