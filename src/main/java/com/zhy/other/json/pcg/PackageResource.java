/*
 * Copyright (c) 2020, QST Innovation Technology Group Co.,Ltd and/or its affiliates. All rights reserved.
 * QST Innovation Technology Group Co.,Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.zhy.other.json.pcg;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 公共：打包资源顶层父类
 *
 * @author cuixiangfei
 * @since 2023-9-20
 */
@Data
public abstract class PackageResource {

    /**
     * 资源名称 (目录/文件名)
     */
    protected String name;

    public PackageResource() {
    }

    protected PackageResource(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RuntimeException("非法的 PackageResource");
        }

        this.name = replaceChar(name);
    }

    public String getName() {
        return name;
    }

    public static String replaceChar(String rep) {
        return rep.replaceAll("[\\\\/:\"|<>^&*-+{}\\[\\]?]", "");
    }

}
