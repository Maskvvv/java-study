package com.zhy.middleware.es.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author zhouhongyin
 * @since 2023/2/25 20:44
 */
@Builder
@Data
public class User {
    private String name;

    private String gender;

    private Integer age;

}
