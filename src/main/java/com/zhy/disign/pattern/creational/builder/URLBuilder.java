package com.zhy.disign.pattern.creational.builder;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:生成器模式练习
 * 用Builder模式编写一个URLBuilder
 *
 * String url = URLBuilder.builder() // 创建Builder
 *         .setDomain("www.liaoxuefeng.com") // 设置domain
 *         .setScheme("https") // 设置scheme
 *         .setPath("/") // 设置路径
 *         .setQuery(Map.of("a", "123", "q", "K&R")) // 设置query
 *         .build(); // 完成build
 *
 *
 * @author: zhouhongyin
 * @time: 2021/8/13 15:24
 */

public class URLBuilder {

    private String domain;
    private String scheme;
    private String path;
    private String query;

    public String getDomain() {
        return domain;
    }

    public String getScheme() {
        return scheme;
    }

    public String getPath() {
        return path;
    }

    public String getQuery() {
        return query;
    }

    private URLBuilder(URLBuilderBuild urlBuilderBuild){
        domain = urlBuilderBuild.domain;
        scheme = urlBuilderBuild.scheme;
        path = urlBuilderBuild.path;
        query = urlBuilderBuild.query;

    }

    public static URLBuilderBuild builder() {
        return new URLBuilderBuild();
    }



    public static class URLBuilderBuild {

        private String domain;
        private String scheme;
        private String path;
        private String query;

        public URLBuilderBuild setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        public URLBuilderBuild setScheme(String scheme) {
            this.scheme = scheme+"://";
            return this;

        }

        public URLBuilderBuild setPath(String path) {
            this.path = path;
            return this;

        }

        public URLBuilderBuild setQuery(Map<String, String> query) {
            StringBuffer stringBuffer = new StringBuffer("?");
            query.forEach((key, value) -> {
                stringBuffer.append(key);
                stringBuffer.append("=");
                stringBuffer.append(value);
                stringBuffer.append("&");
            });
            this.query = stringBuffer.toString();
            return this;
        }

        public String build(){
            StringBuffer stringBuffer = new StringBuffer(this.scheme);
            stringBuffer.append(domain);
            stringBuffer.append(path);
            stringBuffer.append(query);
            return stringBuffer.toString();
        }
    }
}


