package com.zhy.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.stereotype.Component;

/**
 * @author zhouhongyin
 * @since 2022/8/24 9:23
 */
@Slf4j
@Component
public class MyRabbitListenerErrorHandler implements RabbitListenerErrorHandler {
    @Override
    public Object handleError(Message amqpMessage, org.springframework.messaging.Message<?> message, ListenerExecutionFailedException exception) throws Exception {

        log.info("-----------------------MyRabbitListenerErrorHandler begin---------------");

        log.info("amqpMessage:{}", amqpMessage);
        log.info("message:{}", message);
        log.info("exception:{}", exception.getMessage());

        log.info("-----------------------MyRabbitListenerErrorHandler end---------------");

        return "消费失败了";
    }
}
