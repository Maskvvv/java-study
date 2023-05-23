package com.zhy.java基础.spi;

import java.util.ServiceLoader;

/**
 * @author zhouhongyin
 * @since 2023/5/23 22:01
 */
public class SpiTest {

    public static void main(String[] args) {
        ServiceLoader<UploadCDN> uploadCDN = ServiceLoader.load(UploadCDN.class);

        for (UploadCDN u : uploadCDN) {
            u.upload("filePath");
        }

    }

}
