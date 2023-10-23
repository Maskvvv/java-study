package com.zhy.other.http;

import com.alibaba.fastjson.JSON;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/10/20 17:26
 */
public class OkHttp {

    public static void main(String[] args) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");

        String bodyString = "{\"applyRecordId\":\"18e0c5f81fbc4ec58c449d9d2b955b60\",\"competitionId\":\"7f236f08313a4af48d1684ed36544763\",\"trackId\":\"f6351d2c5eb24af2ac17b80e2b09fc13\",\"stageId\":\"3249164fc1694faa9af26c2c7130cf14\",\"areaId\":\"a7954bffbbf24105a7896ae6304c209d\",\"applyInfo\":{\"name\":\"张莱莱\",\"phone\":\"18617288987\",\"email\":\"test11@12.cn\",\"school\":\"青岛大学\",\"schoolCode\":\"4137011065\",\"college\":\"计算机学院\",\"major\":\"计算机科学与技术\",\"majorCode\":\"080901\",\"grade\":2022,\"degree\":\"本科\"}}";
        RequestBody body = RequestBody.create(mediaType, bodyString.getBytes(StandardCharsets.UTF_8));

        Request request = new Request.Builder()
                .url("http://127.0.0.1:8605/apply_records/team/member")
                .method("POST", body)
                .addHeader("X-Access-Token", "NDUxYjE5Y2YtZmMzYS00ZTEwLTg1ZGYtOTAyZTRjNDA2M2Zl")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void test1() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        client.readTimeoutMillis();
        MediaType mediaType = MediaType.parse("application/json");

        String bodyString = "{\"applyRecordId\":\"18e0c5f81fbc4ec58c449d9d2b955b60\",\"competitionId\":\"7f236f08313a4af48d1684ed36544763\",\"trackId\":\"f6351d2c5eb24af2ac17b80e2b09fc13\",\"stageId\":\"3249164fc1694faa9af26c2c7130cf14\",\"areaId\":\"a7954bffbbf24105a7896ae6304c209d\",\"applyInfo\":{\"name\":\"张莱莱\",\"phone\":\"18617288987\",\"email\":\"test11@12.cn\",\"school\":\"青岛大学\",\"schoolCode\":\"4137011065\",\"college\":\"计算机学院\",\"major\":\"计算机科学与技术\",\"majorCode\":\"080901\",\"grade\":2022,\"degree\":\"本科\"}}";
        RequestBody body = RequestBody.create(mediaType, bodyString.getBytes(StandardCharsets.UTF_8));

        Request request = new Request.Builder()
                .url("http://127.0.0.1:8605/apply_records/team/member")
                .method("POST", body)
                .addHeader("X-Access-Token", "NDUxYjE5Y2YtZmMzYS00ZTEwLTg1ZGYtOTAyZTRjNDA2M2Zl")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();

        System.out.println(JSON.toJSONString(response));


    }


}
