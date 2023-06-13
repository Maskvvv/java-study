package com.zhy.middleware.es.option;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.suggest.Suggest;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zhouhongyin
 * @since 2023/2/25 20:30
 */
@Slf4j
public class ESDocSearch {

    RestHighLevelClient client;

    @BeforeEach
    public void client() {
        client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http"))
        );

    }

    @AfterEach
    public void after() throws IOException {
        client.close();
    }


    /**
     * 全量查询
     */
    @Test
    public void matchAllQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest("company");

        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //searchRequest.source(sourceBuilder);

        searchRequest.source().query(QueryBuilders.matchAllQuery());

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }

    }

    /**
     * match 查询
     */
    @Test
    public void matchQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest("company");

        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //searchRequest.source(sourceBuilder);

        searchRequest.source().query(QueryBuilders.matchQuery("name", "上海"));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    /**
     * multiMatchQuery 查询
     */
    @Test
    public void multiMatchQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest("company");

        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //searchRequest.source(sourceBuilder);

        searchRequest.source().query(QueryBuilders.multiMatchQuery("上海", "name"));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            System.out.println(sourceAsString);
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void termQuery() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");

        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.termQuery("age", 10));
        //searchRequest.source(sourceBuilder);

        searchRequest.source().query(QueryBuilders.termQuery("age", 10));

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }


    }

    /**
     * 分页查询
     */
    @Test
    public void pageSearch() throws IOException {


        SearchRequest searchRequest = new SearchRequest("company");

        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //sourceBuilder.from(0).size(2);
        //searchRequest.source(sourceBuilder);

        searchRequest.source().query(QueryBuilders.matchAllQuery());
        searchRequest.source().from(0).size(2);


        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }

    }

    /**
     * 排序查询
     */
    @Test
    public void sortSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("company");

        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //sourceBuilder.sort("age", SortOrder.DESC);
        //
        //searchRequest.source(sourceBuilder);

        searchRequest.source().query(QueryBuilders.matchAllQuery());
        searchRequest.source().sort("age", SortOrder.DESC);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }

    }

    /**
     * 过滤查询
     */
    @Test
    public void filterSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");
        String[] includes = {"name"};
        String[] excludes = {};
        // 构建查询条件
        //SearchSourceBuilder sourceBuilder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //sourceBuilder.fetchSource(includes, excludes);

        searchRequest.source().query(QueryBuilders.matchAllQuery());
        searchRequest.source().fetchSource(includes, excludes);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }
    }

    /**
     * 组合查询
     */
    @Test
    public void combineSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");

        // 构建查询条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("age", 10));
        boolQueryBuilder.must(QueryBuilders.matchQuery("gender", "男"));

        boolQueryBuilder.should(QueryBuilders.matchQuery("gender", "女"));

        searchRequest.source().query(boolQueryBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }

    }

    /**
     * 范围查询
     */
    @Test
    public void rangeSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");

        // 构建查询条件
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("age");
        rangeQueryBuilder.gte(11);
        rangeQueryBuilder.lt(13);

        searchRequest.source().query(rangeQueryBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }

    }

    /**
     * 模糊查询
     */
    @Test
    public void fuzzySearch() throws IOException {
        SearchRequest searchRequest = new SearchRequest("user");

        // 构建查询条件
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "张").fuzziness(Fuzziness.ONE);

        searchRequest.source().query(fuzzyQueryBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {
            System.out.println(hit);
        }


    }

    /**
     * 高亮查询
     */
    @Test
    public void highlightSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("company");

        // 构建查询条件
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("name", "上海").fuzziness(Fuzziness.ONE);
        searchRequest.source().query(fuzzyQueryBuilder);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<front color='red'>");
        highlightBuilder.postTags("</front>");
        highlightBuilder.field("name");
        searchRequest.source().highlighter(highlightBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println(hits.getTotalHits());

        for (SearchHit hit : hits) {

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlightField = highlightFields.get("name");
            Text[] fragments = highlightField.getFragments();
            for (Text fragment : fragments) {
                System.out.println(fragment.string());
            }
        }



    }

    /**
     * 聚合查询
     */
    @Test
    public void aggSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");

        // 构建查询条件
        AggregationBuilder aggregationBuilder = AggregationBuilders.max("maxAge").field("age");

        searchRequest.source().aggregation(aggregationBuilder);


        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        for (Aggregation aggregation : response.getAggregations()) {
            System.out.println(aggregation.getName());
        }
        SearchHits hits = response.getHits();


        for (SearchHit hit : hits) {
            System.out.println(hit);
        }


    }

    /**
     * 分组查询
     */
    @Test
    public void groupSearch() throws IOException {

        SearchRequest searchRequest = new SearchRequest("user");

        // 构建查询条件
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("groupAge").field("age");

        searchRequest.source().aggregation(aggregationBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        for (Aggregation aggregation : response.getAggregations()) {
            System.out.println(aggregation.getName());
            System.out.println(aggregation.getMetadata());
        }
        SearchHits hits = response.getHits();


        for (SearchHit hit : hits) {
            System.out.println(hit);
        }

    }

    /**
     * suggest 查询
     */
    @Test
    public void suggest() throws IOException {
        SearchRequest searchRequest = new SearchRequest("company");

        SuggestBuilder suggestBuilder = new SuggestBuilder();
        suggestBuilder.addSuggestion(
                "suggest",
                SuggestBuilders.completionSuggestion("keyword")
                        .prefix("泰盈").skipDuplicates(true).size(10));

        searchRequest.source().suggest(suggestBuilder);

        // 构建查询条件
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        Suggest suggest = response.getSuggest();

        List<? extends Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option>> entries = suggest.getSuggestion("suggest").getEntries();

        for (Suggest.Suggestion.Entry<? extends Suggest.Suggestion.Entry.Option> entry : entries) {
            for (Suggest.Suggestion.Entry.Option option : entry.getOptions()) {
                String keyword = option.getText().string();
                System.out.println(keyword);

            }
        }
    }


}
