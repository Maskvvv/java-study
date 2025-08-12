package com.zhy.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户Mapper接口
 * 
 * @author zhy
 * @since 2025-08-11
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据年龄范围查询用户
     * 
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 用户列表
     */
    List<User> findByAgeRange(@Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge);
}