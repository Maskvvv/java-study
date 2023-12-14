/*
 * Copyright (c) 2020, QST Innovation Technology Group Co.,Ltd and/or its affiliates. All rights reserved.
 * QST Innovation Technology Group Co.,Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zhy.other.json.pcg;


import org.apache.commons.lang3.ObjectUtils;

/**
 * 公共：文件
 *
 * @author cuixiangfei
 * @since 2023-9-20
 */
public class PackageFile extends PackageResource {

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文件 ResourceTypes
     */
    private String resourceType;

    private PackageFile() {
    }

    private PackageFile(String name) {
        super(name);
    }

    private PackageFile(String name, String url, String resourceType) {
        super(name);
        this.url = url;
        this.resourceType = resourceType;
    }

    public String getUrl() {
        return url;
    }

    private void setUrl(String url) {
        this.url = url;
    }

    public String getResourceType() {
        return resourceType;
    }

    private void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public static final class Builder {
        private String url;
        private String resourceType;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder resourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public PackageFile build() {
            if (ObjectUtils.anyNull(url, resourceType, name)) {
                throw new RuntimeException("非法的 PackageFile");
            }
            return new PackageFile(name, url, resourceType);
        }
    }

}
