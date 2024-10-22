package com.zhy.other.statemachine;

import com.alibaba.cola.statemachine.builder.StateMachineBuilder;
import com.alibaba.cola.statemachine.builder.StateMachineBuilderFactory;

public class OrderOfSaleStateMachine {
    public void initialize() {

        //对状态机开始构建，并在StateMachineFactory中注册
        StateMachineBuilder<OrderStatusEnum, OrderEventEnum, OrderContext> builder
                = StateMachineBuilderFactory.create();


        /**
         * 创建订单: 初始化 -> 待支付
         * externalTransition : 用于一个流转的构建器
         */
        builder.externalTransition()
                .from(OrderStatusEnum.INIT)
                .to(OrderStatusEnum.PAY_ONLINE)
                .on(OrderEventEnum.CREATE_ORDER)
                .when((condition) -> true)
                .perform((from, to, event, context) -> {
                    System.out.println("1");
                    System.out.println(context);

                });


        //builder.externalTransition()
        //        .from(OrderStatusEnum.PART_DELIVERY)
        //        .to(OrderStatusEnum.PAY_ONLINE)
        //        .on(OrderEventEnum.DELIVERY_PART)
        //        .when((condition) -> true)
        //        .perform((from, to, event, context) -> {
        //            System.out.println("4");
        //            System.out.println(context);
        //
        //        });

        /**
         * 部分发货: 部分发货
         * internalTransition : 内部流转
         */
        builder.internalTransition()
                .within(OrderStatusEnum.PART_DELIVERY)
                .on(OrderEventEnum.DELIVERY_PART)
                .when((condition) -> true)
                .perform((from, to, event, context) -> {
                    System.out.println("3");

                });

        /**
         * 取消订单: 待支付、待发货、待收货 -> 待支付
         * externalTransitions : 用于多个流转的构建器
         */
        builder.externalTransitions()
                .fromAmong(OrderStatusEnum.PAY_ONLINE, OrderStatusEnum.WAITING_FOR_RECEIVED, OrderStatusEnum.WAITING_DELIVERY)
                .to(OrderStatusEnum.CANCEL)
                .on(OrderEventEnum.CANCEL_ORDER)
                .when((condition) -> true)
                .perform((from, to, event, context) -> {
                    System.out.println("5");

                });


        // 说明：cola statemachine 构建后，会自动 StateMachineFactory 注册。
        // 构建成功后根据 StateMachineFactory.get(machineId) 获取状态机，不允许重复构建，重复构建会报错。
        builder.build(this.getStateMachineIdEnum().getValue());
    }

    public OrderStateMachineIdEnum getStateMachineIdEnum() {
        return OrderStateMachineIdEnum.ORDER_OF_SALE;
    }
}
