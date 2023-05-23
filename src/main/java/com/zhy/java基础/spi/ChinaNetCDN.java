package com.zhy.java基础.spi;

public class ChinaNetCDN implements UploadCDN {

    @Override
    public void upload(String url) {
        System.out.println("upload to chinaNet cdn");
    }

    @Override
    public String getName() {
        return "ChinaNetCDN";
    }
}
