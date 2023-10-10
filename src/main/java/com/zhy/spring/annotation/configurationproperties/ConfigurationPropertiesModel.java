package com.zhy.spring.annotation.configurationproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhouhongyin
 * @since 2023/2/21 13:55
 */
@Component
@ConfigurationProperties("catalog")
public class ConfigurationPropertiesModel {

    private String name;

    private List<String> array;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getArray() {
        return array;
    }

    public void setArray(List<String> array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "ConfigurationPropertiesModel{" +
                "name='" + name + '\'' +
                ", array=" + array +
                '}';
    }
}
