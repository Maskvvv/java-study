package com.zhy.other.typesafe;

import java.util.List;
import java.util.Map;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/9/25 16:45
 */
public class TypeSafeModel {

    private String name;
    private Map<String, Object> map;
    private List<String> list;
    private Type type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {

        MAY_SQL,
        REDIS;

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

}
