package com.zhy.middleware.rabbitmq.type;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

/**
 * @author zhouhongyin
 * @since 2023/3/28 21:49
 */
public class OureaRabbitMQConfig {


    public static final String START_AT_QUEUE = "live.startat.ourea";
    public static final String START_AT_ROUTING_KEY = "live.startat.ourea";

    @Bean
    public Queue oureaStartAtQueue() {
        return new Queue(START_AT_QUEUE);
    }


    @Bean
    public Binding binding(TopicExchange startAtExchange, Queue oureaStartAtQueue) {
        return BindingBuilder.bind(oureaStartAtQueue).to(startAtExchange).with(START_AT_ROUTING_KEY);
    }

}
