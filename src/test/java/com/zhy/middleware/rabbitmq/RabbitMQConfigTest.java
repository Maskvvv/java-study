package com.zhy.middleware.rabbitmq;

import com.zhy.JavaStudyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhouhongyin
 * @since 2022/8/23 17:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaStudyApplication.class)
public class RabbitMQConfigTest{

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    public void publish() {
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_EXCHANGE,RabbitMQConfig.RABBITMQ_ROUTING_KEY,"我是消息");
    }
}
