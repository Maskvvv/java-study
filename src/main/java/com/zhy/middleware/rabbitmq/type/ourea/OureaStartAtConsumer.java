package com.zhy.middleware.rabbitmq.type.ourea;

import com.rabbitmq.client.Channel;
import com.zhy.middleware.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

@ConditionalOnProperty(prefix = "zhy", name = "rabbitmq.enable", havingValue = "true", matchIfMissing = false)
@Component
public class OureaStartAtConsumer {

    /**
     * 消息消费者
     * queues: 指定要消费的队列
     * ackMode：MANUAL AUTO
     * concurrency: 开几个线程消费消息
     */
    @RabbitListener(queues = RabbitMQConfig.RABBITMQ_QUEUE,
            ackMode = "MANUAL",
            concurrency = "2",
            errorHandler = "myRabbitListenerErrorHandler")
    public void onMessage(String msg, Channel channel, Message message) throws IOException {

        try {
            System.out.println("consumer1" + Thread.currentThread() + ":" + msg);
            Random random = new Random();
            Thread.sleep(random.nextInt(3000));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
        }

    }


    /**
     * 队列不存在时，需要创建一个队列，并且与exchange绑定
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.RABBITMQ_QUEUE1, durable = "false", autoDelete = "true"),
            exchange = @Exchange(value = RabbitMQConfig.RABBITMQ_EXCHANGE1, type = ExchangeTypes.TOPIC),
            key = RabbitMQConfig.RABBITMQ_ROUTING_KEY1))
    public void onMessage1(String msg, Channel channel, Message message) {
        System.out.println("consumer2: " + msg);
    }

    /**
     * 队列不存在时，需要创建一个队列，并且与exchange绑定
     * 交换机不存在时创建交换机
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.RABBITMQ_QUEUE2, durable = "false", autoDelete = "true"),
            exchange = @Exchange(value = RabbitMQConfig.RABBITMQ_EXCHANGE2, type = ExchangeTypes.TOPIC, durable = "false", autoDelete = "true"),
            key = RabbitMQConfig.RABBITMQ_ROUTING_KEY2),
            ackMode = "AUTO")
    public void onMessage2(String msg, Channel channel, Message message) {
        System.out.println("consumer3: " + msg);
    }

    /**
     * 队列不存在时，需要创建一个队列，并且与exchange绑定
     * 交换机不存在时创建交换机
     * 延时队列
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitMQConfig.RABBITMQ_QUEUE3, durable = "false", autoDelete = "true"),
            exchange = @Exchange(value = RabbitMQConfig.RABBITMQ_EXCHANGE3,
                    type = ExchangeTypes.TOPIC,
                    durable = "false",
                    autoDelete = "true",
                    delayed = "true"),
            key = RabbitMQConfig.RABBITMQ_ROUTING_KEY3),
            ackMode = "AUTO")
    public void onMessage4(String msg, Channel channel, Message message) {
        System.out.println("consumer4: " + msg);
        System.out.println("consumer4: " + System.currentTimeMillis());
        System.out.println(System.currentTimeMillis() - Long.parseLong(msg) + "\n");
    }


}
