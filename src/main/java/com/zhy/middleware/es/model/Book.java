package com.zhy.middleware.es.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Book Index
 *
 * @author zhouhongyin
 * @since 2023/2/26 10:42
 */
@Data
@Document(indexName = "book", createIndex = true)
public class Book {
    @Id
    @Field(type = FieldType.Text)
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date createTime;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date updateTime;
}
