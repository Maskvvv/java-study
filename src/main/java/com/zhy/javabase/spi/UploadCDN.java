package com.zhy.javabase.spi;


public interface UploadCDN {

    public static final String NAME = "random";

    void upload(String url);

    String getName();
}
