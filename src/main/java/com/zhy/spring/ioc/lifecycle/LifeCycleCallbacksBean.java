package com.zhy.spring.ioc.lifecycle;

import org.springframework.stereotype.Component;

/**
 *
 * @author zhouhongyin
 * @since 2023/1/3 15:49
 */
@Component
public class LifeCycleCallbacksBean {

    public void init() {
        // do some initialization work
    }

    public void destroy() {
        // do some destruction work (like releasing pooled connections)
    }
}
