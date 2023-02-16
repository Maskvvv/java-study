package com.zhy.middleware.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * @author zhouhongyin
 * @since 2022/8/23 17:45
 */
@RestController
@RequestMapping("rabbitmq")
public class RabbitMQController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("publish")
    public void publish(Integer num, String queue) {
        for (int i = 0; i < num; i++) {
            rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_EXCHANGE + queue, RabbitMQConfig.RABBITMQ_ROUTING_KEY + queue, "我是消息" + i);
        }

    }

    @GetMapping("publish/delay")
    public void publishDelay(Integer num, Integer delay, String queue) {
        for (int i = 0; i < num; i++) {
            Message message = MessageBuilder.withBody(((String.valueOf(System.currentTimeMillis()))).getBytes(StandardCharsets.UTF_8)).build();
            message.getMessageProperties().setDelay(delay * 1000);
            rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_EXCHANGE + queue, RabbitMQConfig.RABBITMQ_ROUTING_KEY + queue, message);
        }

    }


}
