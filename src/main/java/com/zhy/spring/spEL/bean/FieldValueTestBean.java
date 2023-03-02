package com.zhy.spring.spEL.bean;

import org.springframework.beans.factory.annotation.Value;

public class FieldValueTestBean {

    @Value("#{ systemProperties['user.region'] }")
    private String defaultLocale;

    @Value("#{ systemProperties['user.region'] }")
    public void setDefaultLocale(String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    public String getDefaultLocale() {
        return this.defaultLocale;
    }

    public void configure(@Value("#{ systemProperties['user.region'] }") String defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

}
