package com.zhy.mybatis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.mybatis.entity.User;
import com.zhy.mybatis.mapper.UserMapper;
import com.zhy.mybatis.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 用户服务实现类
 * 
 * @author zhy
 * @since 2025-08-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Override
    public List<User> findByAgeRange(Integer minAge, Integer maxAge) {
        // 参数校验
        if (minAge == null || maxAge == null || minAge < 0 || maxAge < 0 || minAge > maxAge) {
            return Collections.emptyList();
        }
        return baseMapper.findByAgeRange(minAge, maxAge);
    }

    @Override
    public boolean createUser(User user) {
        return Optional.ofNullable(user)
                .filter(this::validateUser)
                .map(this::save)
                .orElse(false);
    }

    @Override
    public boolean updateUser(User user) {
        return Optional.ofNullable(user)
                .filter(u -> u.getId() != null)
                .filter(this::validateUser)
                .map(this::updateById)
                .orElse(false);
    }

    @Override
    public boolean deleteUser(Long id) {
        return Optional.ofNullable(id)
                .filter(userId -> userId > 0)
                .map(this::removeById)
                .orElse(false);
    }

    /**
     * 验证用户信息
     * 
     * @param user 用户信息
     * @return 是否有效
     */
    private boolean validateUser(User user) {
        return user.getUsername() != null && !user.getUsername().trim().isEmpty() &&
               user.getEmail() != null && !user.getEmail().trim().isEmpty() &&
               user.getAge() != null && user.getAge() > 0 && user.getAge() < 150;
    }
}