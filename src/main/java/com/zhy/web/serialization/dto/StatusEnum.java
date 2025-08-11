package com.zhy.web.serialization.dto;

public enum StatusEnum implements SupEnum {
    ACTIVE(1, "活跃"),
    INACTIVE(0, "不活跃"),
    DELETED(-1, "已删除");

    private final Integer code;
    private final String description;

    StatusEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 根据 code 查找枚举的方法
    public StatusEnum fromCode(Integer code) {
        for (StatusEnum status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status code: " + code);
    }
}