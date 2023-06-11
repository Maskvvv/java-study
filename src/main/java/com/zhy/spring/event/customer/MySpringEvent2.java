package com.zhy.spring.event.customer;

/**
 * 自定义 Spring 事件
 */
public class MySpringEvent2 extends MySpringEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param message 事件消息
     */
    public MySpringEvent2(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage() {
        return getSource();
    }
}
