package com.zhy.spring.注解.value;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/1/10 22:38
 */
@Data
@Component
public class ValueModel {


    private final String catalog;

    @Value("${catalog.array}")
    private String[] array;

    @Value("${catalog.array}")
    private List<String> list;

    public ValueModel(@Value("${catalog.name:222}") String catalog) {
        this.catalog = catalog;
    }

}
