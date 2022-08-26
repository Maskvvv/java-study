package com.zhy.rabbitmq;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String RABBITMQ_EXCHANGE = "rabbitmq.exchange";
    public static final String RABBITMQ_QUEUE = "rabbitmq.queue";
    public static final String RABBITMQ_ROUTING_KEY = "rabbitmq.routingkey";

    public static final String RABBITMQ_EXCHANGE1 = "rabbitmq.exchange1";
    public static final String RABBITMQ_QUEUE1 = "rabbitmq.queue1";
    public static final String RABBITMQ_ROUTING_KEY1 = "rabbitmq.routingkey1";

    public static final String RABBITMQ_EXCHANGE2 = "rabbitmq.exchange2";
    public static final String RABBITMQ_QUEUE2 = "rabbitmq.queue2";
    public static final String RABBITMQ_ROUTING_KEY2 = "rabbitmq.routingkey2";

    public static final String RABBITMQ_EXCHANGE3 = "rabbitmq.exchange3";
    public static final String RABBITMQ_QUEUE3 = "rabbitmq.queue3";
    public static final String RABBITMQ_ROUTING_KEY3 = "rabbitmq.routingkey3";


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RABBITMQ_EXCHANGE,true,false);
    }

    @Bean
    public TopicExchange topicExchange1(){
        return new TopicExchange(RABBITMQ_EXCHANGE1,true,false);
    }

    @Bean
    public Queue queue(){
        return new Queue(RABBITMQ_QUEUE);
    }

    @Bean
    public Binding binding(TopicExchange topicExchange, Queue queue){
        return BindingBuilder.bind(queue).to(topicExchange).with(RABBITMQ_ROUTING_KEY);
    }

    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }


    //@Bean
    //public MessageListenerContainer messageListenerContainer() {
    //    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    //    container.setExposeListenerChannel(true);
    //    container.setPrefetchCount(1);
    //    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
    //    return container;
    //}


    //@Bean("fileTransListenerContainer")
    //public MessageListenerContainer fileTransListenerContainer(@Qualifier("connectionFactory") ConnectionFactory connectionFactory,
    //                                                           @Qualifier("transFileConsumer") TransFileConsumer transFileConsumer) {
    //    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
    //    container.setQueueNames(rabbitMQProperties.getFileTrans().get("queueName"));
    //    container.setExposeListenerChannel(true);
    //    container.setAcknowledgeMode(AcknowledgeMode.AUTO);
    //    container.setMessageListener(transFileConsumer);
    //    return container;
    //}


}
