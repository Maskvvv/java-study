package com.zhy.mybatis.enums;

/**
 * 用户状态枚举
 * 
 * @author zhy
 * @since 2025-08-11
 */
public enum UserStatus implements BaseEnum<Integer> {
    
    /**
     * 禁用状态
     */
    DISABLED(0, "禁用"),
    
    /**
     * 启用状态
     */
    ENABLED(1, "启用");
    
    /**
     * 状态码（数据库存储值）
     */
//    @EnumValue
    private final Integer code;
    
    /**
     * 状态描述
     */
    private final String description;
    
    UserStatus(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    
    /**
     * 获取状态码
     * 
     * @return 状态码
     */
    @Override
//    @JsonValue
    public Integer getCode() {
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
    public static UserStatus fromCode(Integer code) {
        if (code == null) {
            return null;
        }
        
        for (UserStatus status : UserStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        
        throw new IllegalArgumentException("未知的用户状态码: " + code);
    }
    
    /**
     * 判断是否为启用状态
     * 
     * @return true-启用，false-禁用
     */
    public boolean isEnabled() {
        return this == ENABLED;
    }
    
    @Override
    public String toString() {
        return description;
    }
}