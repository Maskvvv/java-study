package com.zhy.mybatis.enums;

/**
 * 枚举基础接口
 * 所有需要与数据库映射的枚举都应该实现此接口
 * 
 * @author zhy
 * @since 2025-08-11
 */
public interface BaseEnum<T> {
    
    /**
     * 获取枚举对应的数据库存储值
     * 
     * @return 数据库存储值
     */
    T getCode();

}