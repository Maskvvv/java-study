package com.zhy.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.zhy.mybatis.enums.TypeString;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.zhy.mybatis.enums.DeleteStatus;
import com.zhy.mybatis.enums.UserStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 
 * @author zhy
 * @since 2025-08-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField("username")
    private String username;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 状态：DISABLED-禁用，ENABLED-启用
     */
    @TableField(value = "status")
    private UserStatus status;

    /**
     * 删除状态：0-未删除，1-已删除
     */
    @TableField(value = "deleted")
    private DeleteStatus deleted;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;


    private TypeString typeString;

    private JsonDto jsonDto;


}