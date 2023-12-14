/*
 * Copyright (c) 2020, QST Innovation Technology Group Co.,Ltd and/or its affiliates. All rights reserved.
 * QST Innovation Technology Group Co.,Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zhy.other.json.pcg;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共：目录
 *
 * @author cuixiangfei
 * @since 2023-9-20
 */
@Data
public class PackageDirectory extends PackageResource {

    /**
     * 资源列表
     */
    private List<PackageResource> resources;

    public PackageDirectory() {
    }

    private PackageDirectory(String name) {
        super(name);
        resources = new ArrayList<>();
    }

    public List<PackageResource> getResources() {
        return resources;
    }

    public PackageDirectory addResource(PackageResource resource) {
        this.resources.add(resource);
        return this;
    }

    public PackageDirectory addResources(List<PackageResource> resources) {
        this.resources.addAll(resources);
        return this;
    }

    public static final class Builder {
        private List<PackageResource> resources;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            Builder builder = new Builder();
            builder.resources = new ArrayList<>();
            return builder;
        }

        public Builder resources(List<PackageResource> resources) {
            this.resources = resources;
            return this;
        }

        public Builder addResource(PackageResource resource) {
            this.resources.add(resource);
            return this;
        }

        public Builder addResources(List<PackageResource> resources) {
            this.resources.addAll(resources);
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public PackageDirectory build() {
            PackageDirectory packageDirectory = new PackageDirectory(name);
            packageDirectory.resources = this.resources;
            return packageDirectory;
        }
    }

}
