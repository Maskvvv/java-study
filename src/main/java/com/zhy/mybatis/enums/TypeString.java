package com.zhy.mybatis.enums;

/**
 * 用户状态枚举
 *
 * @author zhy
 * @since 2025-08-11
 */
public enum TypeString implements BaseEnum<String> {

    /**
     * 禁用状态
     */
    T1("0", "T1"),

    /**
     * 启用状态
     */
    T2("1", "T2");

    /**
     * 状态码（数据库存储值）
     */
//    @EnumValue
    private final String code;

    /**
     * 状态描述
     */
    private final String description;

    TypeString(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 获取状态码
     *
     * @return 状态码
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 获取状态描述
     *
     * @return 状态描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 根据状态码获取枚举
     *
     * @param code 状态码
     * @return 用户状态枚举
     */
    public static TypeString fromCode(String code) {
        if (code == null) {
            return null;
        }

        for (TypeString status : TypeString.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }

        throw new IllegalArgumentException("未知的用户状态码: " + code);
    }

}