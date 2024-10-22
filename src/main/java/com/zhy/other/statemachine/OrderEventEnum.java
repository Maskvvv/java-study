package com.zhy.other.statemachine;

public enum OrderEventEnum {
    CREATE_ORDER(1, "创建订单"),
    REPAY(2, "支付"),
    CANCEL_ORDER(3, "取消订单"),
    TAKE_ORDER(4, "接单"),
    REJECT_ORDER(5, "拒单"),
    DELIVERY_PART(6, "部分发货"),
    DELIVERY_ALL(7, "全部发货"),
    CONFIRM_RECEIPT(8, "确认收货"),
    EXTEND_RECEIPT(9, "延长收货"),
    COMPLETE(10, "交易完成");


    private String des;
    private Integer value;


    OrderEventEnum(Integer value, String des) {
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
