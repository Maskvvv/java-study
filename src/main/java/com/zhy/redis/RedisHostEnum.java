package com.zhy.redis;

/**
 * @description:
 * @author: zhouhongyin
 * @time: 2021/7/27 11:35
 */

public enum RedisHostEnum {
    HSOT("127.0.0.1");

    String host;

    RedisHostEnum() {
    }

    RedisHostEnum(String host) {
        this.host = host;
    }

    public String getHost() {
        return host;
    }
}
