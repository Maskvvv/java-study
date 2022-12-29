package com.zhy.spring.di;

import lombok.AllArgsConstructor;

/**
 * @author zhouhongyin
 * @since 2022/12/12 14:24
 */
@AllArgsConstructor
public enum TestStrategyEunm {
    Parent("parent", "0"),
    Select("select", "1"),
    Insert("insert", "2"),
    Update("update", "3"),
    Delete("delete", "4"),
    ;

    private String name;

    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
