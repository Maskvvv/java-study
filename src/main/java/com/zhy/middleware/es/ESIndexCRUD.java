package com.zhy.middleware.es;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.junit.Test;

import java.io.IOException;

/**
 * @author zhouhongyin
 * @since 2023/2/25 20:30
 */
@Slf4j
public class ESIndexCRUD {

    @Test
    public void create() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        CreateIndexRequest request = new CreateIndexRequest("user");

        CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response);

        client.close();

    }

    @Test
    public void query() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        GetIndexRequest request = new GetIndexRequest("user");

        GetIndexResponse response = client.indices().get(request, RequestOptions.DEFAULT);

        System.out.println(response.getAliases());
        System.out.println(response.getIndices());

        client.close();

    }

    @Test
    public void delete() throws IOException {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

        DeleteIndexRequest request = new DeleteIndexRequest("user");

        AcknowledgedResponse response = client.indices().delete(request, RequestOptions.DEFAULT);

        client.close();

    }


}
