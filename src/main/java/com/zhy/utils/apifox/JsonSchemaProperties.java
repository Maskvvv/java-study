package com.zhy.utils.apifox;

import lombok.Data;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/24 15:09
 */
@Data
public class JsonSchemaProperties {

    private String type;

    private String title;

    private String description;


    public void setType(String type) {
        switch (type) {
            case "String":
                type = "string";
                break;
            case "Int":
                type = "integer";
                break;
            case "Double":
                type = "number";
                break;
            case "Array[String]":
                type = "array";
                break;

        }

        this.type = type;
    }
}
