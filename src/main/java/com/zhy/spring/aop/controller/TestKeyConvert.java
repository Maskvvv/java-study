package com.zhy.spring.aop.controller;

import com.zhy.spring.aop.lock.KeyConvert;
import com.zhy.spring.aop.model.LockBody;
import com.zhy.spring.aop.model.LockParam;
import com.zhy.spring.spEL.model.DomainKey;
import org.springframework.stereotype.Component;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/3 15:45
 */
@Component
public class TestKeyConvert implements KeyConvert {


    @Override
    public String getKey(Object... params) {
        LockParam param = (LockParam) params[0];
        LockBody lockBody = (LockBody) params[1];

        return DomainKey.TestLockKey.key(param.getId() + lockBody.getName() + lockBody.getAge());
    }
}
