package com.zhy.mybatis.enums;

/**
 * 删除状态枚举
 * 
 * @author zhy
 * @since 2025-08-11
 */
public enum DeleteStatus implements BaseEnum<Integer> {
    
    /**
     * 未删除状态
     */
    NOT_DELETED(0, "未删除"),
    
    /**
     * 已删除状态
     */
    DELETED(1, "已删除");
    
    /**
     * 删除状态码（数据库存储值）
     */
//    @EnumValue
    private final Integer code;
    
    /**
     * 状态描述
     */
    private final String description;
    
    DeleteStatus(Integer code, String description) {
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
     * @return 删除状态枚举
     */
//    public static DeleteStatus fromCode(Integer code) {
//        if (code == null) {
//            return null;
//        }
//
//        for (DeleteStatus status : DeleteStatus.values()) {
//            if (status.getValue().equals(code)) {
//                return status;
//            }
//        }
//
//        throw new IllegalArgumentException("未知的删除状态码: " + code);
//    }
    
    /**
     * 判断是否已删除
     * 
     * @return true-已删除，false-未删除
     */
    public boolean isDeleted() {
        return this == DELETED;
    }
    
    @Override
    public String toString() {
        return description;
    }
}