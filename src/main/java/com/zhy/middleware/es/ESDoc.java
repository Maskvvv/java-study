package com.zhy.middleware.es;

import com.alibaba.fastjson.JSON;
import com.zhy.middleware.es.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2023/2/25 20:30
 */
@Slf4j
public class ESDoc {

    @Test
    public void insert() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        IndexRequest request = new IndexRequest("user");
        request.index("user").id("1001");

        User user = User.builder().name("maik").age(10).gender("男").build();
        String json = JSON.toJSONString(user);

        request.source(json, XContentType.JSON);

        IndexResponse response = client.index(request, RequestOptions.DEFAULT);

        System.out.println(response);

        client.close();

    }

    @Test
    public void update() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        UpdateRequest request = new UpdateRequest();
        request.index("user").id("1001");

        request.doc(XContentType.JSON, "gender", "女");

        UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

        System.out.println(response);

        client.close();

    }

    @Test
    public void get() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        GetRequest request = new GetRequest("user");

        request.index("user").id("1001");


        GetResponse response = client.get(request, RequestOptions.DEFAULT);

        log.info(response.getSourceAsString());

        client.close();

    }

    @Test
    public void delete() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        DeleteRequest request = new DeleteRequest("user");

        request.index("user").id("1001");


        DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);

        log.info(response.toString());

        client.close();

    }


}
