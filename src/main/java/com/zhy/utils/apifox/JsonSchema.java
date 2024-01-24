package com.zhy.utils.apifox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2024/1/24 15:09
 */
public class JsonSchema extends HashMap<String, Object> {

    private String type;

    private Map<String, JsonSchemaProperties> properties;

    private String title;

    public JsonSchema(String type, String title) {
        this.put("type", type);
        this.put("title", title);
        this.put("x-apifox-orders", new ArrayList<String>());
        this.put("properties", new HashMap<String, JsonSchemaProperties>());
    }

    public void putProperties(String key, JsonSchemaProperties value) {
        ((Map) this.get("properties")).put(key, value);
        ((List<String>) this.get("x-apifox-orders")).add(key);
    }

}
