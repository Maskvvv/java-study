package com.zhy.middleware.rocketmq;


import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProducerExample {
    private static final Logger logger = LoggerFactory.getLogger(ProducerExample.class);

    public static void main(String[] args) throws Exception {
        //1 创建生成对象 指定生产组
        DefaultMQProducer producer = new DefaultMQProducer("wolfcode-producer");
        //2 设置名字服务的地址
        producer.setNamesrvAddr("10.0.30.144:9876");
        //3 启动生成者
        producer.start();
        //4 创建一个消息
        Message message = new Message("01-hello", "hello,rocketmq".getBytes("utf-8"));
        //5 发送消息
        SendResult sendResult = producer.send(message);
        System.out.println(sendResult);
        //6 关闭连接
        producer.shutdown();
    }
}
