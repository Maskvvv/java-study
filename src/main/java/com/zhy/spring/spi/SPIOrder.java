package com.zhy.spring.spi;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/11/16 17:54
 */
public interface SPIOrder {
    int ORDER_CLOUD = 10;
    int ORDER_LOCAL = 20;
    int ORDER_DATASOURCE = 30;
    int ORDER_REDIS = 40;
    int ORDER_ROCKETMQ = 40;
}
