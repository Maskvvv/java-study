package com.zhy.mybatis;

import com.zhy.mybatis.entity.User;
import com.zhy.mybatis.enums.DeleteStatus;
import com.zhy.mybatis.enums.UserStatus;
import com.zhy.mybatis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 用户服务测试类
 * 
 * @author zhy
 * @since 2025-08-11
 */
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindByStatus() {
        // 创建不同状态的测试用户
        User activeUser = new User()
                .setUsername("activeuser")
                .setEmail("active@example.com")
                .setAge(25)
                .setStatus(UserStatus.ENABLED)
                .setDeleted(DeleteStatus.NOT_DELETED);
        userService.createUser(activeUser);

        User inactiveUser = new User()
                .setUsername("inactiveuser")
                .setEmail("inactive@example.com")
                .setAge(30)
                .setStatus(UserStatus.DISABLED)
                .setDeleted(DeleteStatus.NOT_DELETED);
        userService.createUser(inactiveUser);

        // 测试查找激活用户
        List<User> activeUsers = userService.findByStatus(UserStatus.ENABLED);
        assertFalse(activeUsers.isEmpty(), "应该有激活用户");
        assertTrue(activeUsers.stream().allMatch(u -> u.getStatus() == UserStatus.ENABLED), "所有用户状态应该为ENABLED");

        // 测试查找非激活用户
        List<User> inactiveUsers = userService.findByStatus(UserStatus.DISABLED);
        assertFalse(inactiveUsers.isEmpty(), "应该有非激活用户");
        assertTrue(inactiveUsers.stream().allMatch(u -> u.getStatus() == UserStatus.DISABLED), "所有用户状态应该为DISABLED");
    }

    @Test
    public void testFindByAgeRange() {
        // 创建不同年龄的测试用户
        User youngUser = new User()
                .setUsername("younguser")
                .setEmail("young@example.com")
                .setAge(20)
                .setStatus(UserStatus.ENABLED)
                .setDeleted(DeleteStatus.NOT_DELETED);
        userService.createUser(youngUser);

        User middleUser = new User()
                .setUsername("middleuser")
                .setEmail("middle@example.com")
                .setAge(30)
                .setStatus(UserStatus.ENABLED)
                .setDeleted(DeleteStatus.NOT_DELETED);
        userService.createUser(middleUser);

        User oldUser = new User()
                .setUsername("olduser")
                .setEmail("old@example.com")
                .setAge(40)
                .setStatus(UserStatus.ENABLED)
                .setDeleted(DeleteStatus.NOT_DELETED);
        userService.createUser(oldUser);

        // 测试年龄范围查询
        List<User> usersInRange = userService.findByAgeRange(25, 35);
        assertFalse(usersInRange.isEmpty(), "应该有符合年龄范围的用户");
        assertTrue(usersInRange.stream().allMatch(u -> u.getAge() >= 25 && u.getAge() <= 35),
                "所有用户年龄应该在25-35之间");

        // 测试无效参数
        List<User> emptyResult = userService.findByAgeRange(null, 30);
        assertTrue(emptyResult.isEmpty(), "无效参数应该返回空列表");

        List<User> invalidRange = userService.findByAgeRange(40, 20);
        assertTrue(invalidRange.isEmpty(), "无效范围应该返回空列表");
    }

}