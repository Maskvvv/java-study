package com.zhy.javabase.spi;

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
