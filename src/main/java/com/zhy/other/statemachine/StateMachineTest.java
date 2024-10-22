package com.zhy.other.statemachine;

import com.alibaba.cola.statemachine.StateMachine;
import com.alibaba.cola.statemachine.StateMachineFactory;

/**
 * @author zhouhongyin
 * @since 2024/10/22 11:35
 */
public class StateMachineTest {

    public static void main(String[] args) {
        OrderOfSaleStateMachine orderOfSaleStateMachine = new OrderOfSaleStateMachine();
        orderOfSaleStateMachine.initialize();

        StateMachine<OrderStatusEnum, OrderEventEnum, OrderContext> stateMachine = StateMachineFactory.get(OrderStateMachineIdEnum.ORDER_OF_SALE.getValue());

        OrderContext context = new OrderContext();
        context.setValue("test");
        //stateMachine.fireEvent(OrderStatusEnum.INIT, OrderEventEnum.CREATE_ORDER, context);
        stateMachine.fireEvent(OrderStatusEnum.PART_DELIVERY, OrderEventEnum.DELIVERY_PART, context);
        //System.out.println(stateMachine.generatePlantUML());
    }
}
