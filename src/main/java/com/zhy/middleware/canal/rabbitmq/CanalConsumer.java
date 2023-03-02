package com.zhy.middleware.canal.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Component
@ConditionalOnProperty(prefix = "zhy", name = "rabbitmq.enable", havingValue = "true", matchIfMissing = false)
public class CanalConsumer {

    @Resource
    private RestHighLevelClient client;

    //@PostConstruct
    private void createIndex() {
        CreateIndexRequest request = new CreateIndexRequest("canal");

        try {
            CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 消息消费者
     * queues: 指定要消费的队列
     * ackMode：MANUAL AUTO
     * concurrency: 开几个线程消费消息
     */
    @RabbitListener(queues = "canal.athena.canal",
            ackMode = "MANUAL",
            concurrency = "1")
    public void onMessage(String msg, Channel channel, Message message) throws IOException {
        try {
            String body = new String(message.getBody());

            JSONObject bodyObject = JSON.parseObject(body);

            JSONArray jsonArray = bodyObject.getJSONObject("_source").getJSONArray("data");

            for (Object dat : jsonArray) {

                IndexRequest request = new IndexRequest("canal");

                request.source(JSON.toJSONString(dat), XContentType.JSON);

                client.index(request, RequestOptions.DEFAULT);

            }

            log.info( "{}-message:{}", Thread.currentThread(), msg);

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        } catch (Exception e) {
            e.printStackTrace();
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
        }

    }

}
