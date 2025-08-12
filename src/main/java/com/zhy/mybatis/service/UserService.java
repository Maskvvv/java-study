package com.zhy.mybatis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhy.mybatis.entity.User;
import com.zhy.mybatis.enums.UserStatus;

import java.util.List;

/**
 * 用户服务接口
 * 
 * @author zhy
 * @since 2025-08-11
 */
public interface UserService extends IService<User> {

    /**
     * 根据年龄范围查询用户
     * 
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 用户列表
     */
    List<User> findByAgeRange(Integer minAge, Integer maxAge);

    /**
     * 创建用户
     * 
     * @param user 用户信息
     * @return 是否成功
     */
    boolean createUser(User user);

    /**
     * 更新用户信息
     * 
     * @param user 用户信息
     * @return 是否成功
     */
    boolean updateUser(User user);

    /**
     * 删除用户（逻辑删除）
     * 
     * @param id 用户ID
     * @return 是否成功
     */
    boolean deleteUser(Long id);

    /**
     * 根据状态查询用户列表（分页）
     * 
     * @param status 用户状态
     * @param current 当前页
     * @param size 每页大小
     * @return 用户分页列表
     */
    IPage<User> findByStatus(UserStatus status, long current, long size);

    /**
     * 根据状态查询用户列表
     * 
     * @param status 用户状态
     * @return 用户列表
     */
    List<User> findByStatus(UserStatus status);
}