package com.zhy.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhy.mybatis.enums.DeleteStatus;
import com.zhy.mybatis.enums.TypeString;
import com.zhy.mybatis.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户实体类
 * 
 * @author zhy
 * @since 2025-08-11
 */
@Data
public class JsonDto {


    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

}