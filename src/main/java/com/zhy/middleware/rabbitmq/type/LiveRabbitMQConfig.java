package com.zhy.middleware.rabbitmq.type;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LiveRabbitMQConfig {

    /**
     * 服务.通知类型
     */
    public static final String START_AT_EXCHANGE = "live.startat";

    @Bean
    public TopicExchange startAtExchange(){
        return new TopicExchange(START_AT_EXCHANGE,true,false);
    }


    @Bean("startAtRabbitTemplate")
    public RabbitTemplate startAtRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(START_AT_EXCHANGE);
        return rabbitTemplate;
    }

}
