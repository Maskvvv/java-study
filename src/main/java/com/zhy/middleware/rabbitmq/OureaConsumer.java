package com.zhy.middleware.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@ConditionalOnProperty(prefix = "zhy", name = "rabbitmq.enable", havingValue = "true", matchIfMissing = false)
@Component
public class OureaConsumer {

    /**
     * 队列不存在时，需要创建一个队列，并且与exchange绑定
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "ourea.queue", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "ourea.exchange", type = ExchangeTypes.TOPIC),
            key = ""))
    public void onMessage1(String msg, Channel channel, Message message) {
        System.out.println("consumer2: " + msg);
    }



}
