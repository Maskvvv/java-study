package com.zhy.other.statemachine;

public enum OrderStateMachineIdEnum {
    ORDER_OF_SALE("ORDER_OF_SALE", "订单状态机");


    private String value;
    private String des;

    OrderStateMachineIdEnum(String value, String des) {
        this.value = value;
        this.des = des;
    }

    public String getValue() {
        return value;
    }

    public String getDes() {
        return des;
    }
}
