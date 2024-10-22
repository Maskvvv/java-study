package com.zhy.other.statemachine;

public enum OrderStatusEnum {
    INIT("初始化", 0),
    PAY_ONLINE("待支付", 1),
    WAITING_FOR_RECEIVED("待接单", 2),
    WAITING_DELIVERY("待发货", 3),
    PART_DELIVERY("部分发货", 4),
    DELIVER_ALL("待收货", 5),
    RECEIVED("已收货", 6),
    DONE("已完成", 7),
    CANCEL("已关闭", 8);

    private String des;
    private Integer value;


    OrderStatusEnum(String des, Integer value) {
        this.des = des;
        this.value = value;
    }

    public String getDes() {
        return des;
    }

    public Integer getValue() {
        return value;
    }
}
