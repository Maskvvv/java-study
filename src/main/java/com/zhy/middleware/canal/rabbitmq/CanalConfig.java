package com.zhy.middleware.canal.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@ConditionalOnProperty(prefix = "zhy", name = "rabbitmq.enable", havingValue = "true", matchIfMissing = false)
@Configuration
public class CanalConfig {

    @Bean
    Queue canalQueue(){
        return  new Queue("canal_queue");
    }

    @Bean
    DirectExchange canalDirectExchange(){
        return new DirectExchange("canal_exchange");
    }

    @Bean
    Binding canalBinding(){
        return BindingBuilder.bind(canalQueue()).to(canalDirectExchange()).with("canal_key");
    }
}
