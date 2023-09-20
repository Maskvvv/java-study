package com.zhy.middleware.rocketmq;


import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ProducerExample {
    private static final Logger logger = LoggerFactory.getLogger(ProducerExample.class);

    @Test
    public void producer() throws Exception {
        //1 创建生成对象 指定生产组
        DefaultMQProducer producer = new DefaultMQProducer("my-producer");
        //2 设置名字服务的地址
        producer.setNamesrvAddr("10.0.30.144:9876");
        //3 启动生成者
        producer.start();
        //4 创建一个消息
        Message message = new Message("01-hello", "hello,rocketmq".getBytes(StandardCharsets.UTF_8));
        //5 发送消息
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
        //6 关闭连接
        producer.shutdown();
    }




    @Test
    public void consumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("my-consumer");
        consumer.setNamesrvAddr("10.0.30.144:9876");
        consumer.start();

        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                return null;
            }
        });

    }
}
