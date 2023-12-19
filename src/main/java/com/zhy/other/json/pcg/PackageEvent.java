package com.zhy.other.json.pcg;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p> 打包任务 </p>
 *
 * @author zhouhongyin
 * @since 2023/12/13 16:12
 */
public class PackageEvent {

    /**
     * 任务名称
     */
    private String name;

    /**
     * 打包后文件名称
     */
    private String packageName;

    /**
     * 打包后上传的 ResourceTypes
     */
    private String resourceType;

    /**
     * 华为云 resourceId
     */
    private String resourceId;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 资源列表
     */
    private List<PackageResource> resources;

    private PackageEvent() {
    }


    /**
     * 添加资源
     */
    public PackageEvent addResource(PackageResource resource) {
        this.resources.add(resource);
        return this;
    }

    public PackageEvent addResources(List<PackageResource> resources) {
        this.resources.addAll(resources);
        return this;
    }

    public String getSignature() {
        return DigestUtils.md5DigestAsHex((String.valueOf(Objects.hash(resourceType, resourceId, resources)).getBytes()));
    }

    public static final class Builder {
        private String name;
        private String packageName;
        private String resourceType;
        private String resourceId;
        private String createBy;
        private List<PackageResource> resources;

        private Builder() {
        }

        public static Builder builder() {
            Builder builder = new Builder();
            builder.resources = new ArrayList<>();
            return builder;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder packageName(String packageName) {
            this.packageName = packageName;
            return this;
        }

        public Builder resourceType(String resourceType) {
            this.resourceType = resourceType;
            return this;
        }

        public Builder resourceId(String resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        public Builder createBy(String createBy) {
            this.createBy = createBy;
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

        public PackageEvent build() {
            if (StringUtils.isAnyBlank(this.name, this.packageName)) {
                throw new RuntimeException("非法的 PackageEvent");
            }

            PackageEvent packageEvent = new PackageEvent();
            packageEvent.resourceId = this.resourceId;
            packageEvent.name = this.name;
            packageEvent.resourceType = this.resourceType;
            packageEvent.resources = this.resources;
            packageEvent.packageName = this.packageName;
            packageEvent.createBy = this.createBy;
            return packageEvent;
        }
    }

    @Override
    public String toString() {
        return "PackageEvent{" +
                "name='" + name + '\'' +
                ", packageName='" + packageName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }
}
