package com.zhy.other.statemachine;

import com.alibaba.cola.statemachine.Action;

public class CreateOrderAction implements Action<OrderStatusEnum, OrderEventEnum, OrderContext> {
    @Override
    public void execute(OrderStatusEnum from, OrderStatusEnum to, OrderEventEnum event, OrderContext context) {
        // ...
        // 1.获取状态机
        //StateMachine<OrderStatusEnum, OrderEventEnum, OrderContext> stateMachine = StateMachineFactory.get(orderOfSaleStateMachine.getStateMachineIdEnum().getValue());
        //// 2.组状态机 messageContext
        //OrderContext orderContext = buildContext();
        //// 3.状态机事件触发
        //stateMachine.fireEvent(OrderStatusEnum.INIT, OrderEventEnum.CREATE_ORDER, orderContext);
        // ...
    }


}
