package com.zhy.spring.spi;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/11/20 9:08
 */
//@Component
public class SPIService {
    @Autowired
    private SPIBean spiBean;

    public SPIService(SPIBean spiBean) {
        this.spiBean = spiBean;
    }
}
