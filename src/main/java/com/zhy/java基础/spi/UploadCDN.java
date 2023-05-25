package com.zhy.java基础.spi;


public interface UploadCDN {

    public static final String NAME = "random";

    void upload(String url);

    String getName();
}
