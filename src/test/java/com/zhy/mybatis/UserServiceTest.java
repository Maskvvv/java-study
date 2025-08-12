package com.zhy.mybatis;

import com.zhy.mybatis.entity.User;
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
    public void testCreateUser() {
        // 创建测试用户
        User user = new User()
                .setUsername("testuser")
                .setEmail("test@example.com")
                .setAge(25)
                .setStatus(1);

        // 测试创建用户
        boolean result = userService.createUser(user);
        assertTrue(result, "用户创建应该成功");

        // 验证用户是否创建成功
        User savedUser = userService.findByUsername("testuser");
        assertNotNull(savedUser, "保存的用户不应为空");
        assertEquals("testuser", savedUser.getUsername());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals(25, savedUser.getAge());
    }

    @Test
    public void testFindByUsername() {
        // 创建测试用户
        User user = new User()
                .setUsername("findtest")
                .setEmail("findtest@example.com")
                .setAge(30)
                .setStatus(1);
        userService.createUser(user);

        // 测试根据用户名查找
        User foundUser = userService.findByUsername("findtest");
        assertNotNull(foundUser, "应该能找到用户");
        assertEquals("findtest", foundUser.getUsername());

        // 测试查找不存在的用户
        User notFoundUser = userService.findByUsername("notexist");
        assertNull(notFoundUser, "不存在的用户应该返回null");
    }

    @Test
    public void testFindByStatus() {
        // 创建不同状态的测试用户
        User activeUser = new User()
                .setUsername("activeuser")
                .setEmail("active@example.com")
                .setAge(25)
                .setStatus(1);
        userService.createUser(activeUser);

        User inactiveUser = new User()
                .setUsername("inactiveuser")
                .setEmail("inactive@example.com")
                .setAge(30)
                .setStatus(0);
        userService.createUser(inactiveUser);

        // 测试查找激活用户
        List<User> activeUsers = userService.findByStatus(1);
        assertFalse(activeUsers.isEmpty(), "应该有激活用户");
        assertTrue(activeUsers.stream().allMatch(u -> u.getStatus() == 1), "所有用户状态应该为1");

        // 测试查找非激活用户
        List<User> inactiveUsers = userService.findByStatus(0);
        assertFalse(inactiveUsers.isEmpty(), "应该有非激活用户");
        assertTrue(inactiveUsers.stream().allMatch(u -> u.getStatus() == 0), "所有用户状态应该为0");
    }

    @Test
    public void testFindByAgeRange() {
        // 创建不同年龄的测试用户
        User youngUser = new User()
                .setUsername("younguser")
                .setEmail("young@example.com")
                .setAge(20)
                .setStatus(1);
        userService.createUser(youngUser);

        User middleUser = new User()
                .setUsername("middleuser")
                .setEmail("middle@example.com")
                .setAge(30)
                .setStatus(1);
        userService.createUser(middleUser);

        User oldUser = new User()
                .setUsername("olduser")
                .setEmail("old@example.com")
                .setAge(40)
                .setStatus(1);
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

    @Test
    public void testUpdateUser() {
        // 创建测试用户
        User user = new User()
                .setUsername("updatetest")
                .setEmail("update@example.com")
                .setAge(25)
                .setStatus(1);
        userService.createUser(user);

        // 获取保存的用户
        User savedUser = userService.findByUsername("updatetest");
        assertNotNull(savedUser);

        // 更新用户信息
        savedUser.setAge(30).setEmail("updated@example.com");
        boolean updateResult = userService.updateUser(savedUser);
        assertTrue(updateResult, "用户更新应该成功");

        // 验证更新结果
        User updatedUser = userService.findByUsername("updatetest");
        assertEquals(30, updatedUser.getAge());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        // 创建测试用户
        User user = new User()
                .setUsername("deletetest")
                .setEmail("delete@example.com")
                .setAge(25)
                .setStatus(1);
        userService.createUser(user);

        // 获取保存的用户
        User savedUser = userService.findByUsername("deletetest");
        assertNotNull(savedUser);

        // 删除用户
        boolean deleteResult = userService.deleteUser(savedUser.getId());
        assertTrue(deleteResult, "用户删除应该成功");

        // 验证用户已被逻辑删除
        User deletedUser = userService.findByUsername("deletetest");
        assertNull(deletedUser, "删除的用户应该查找不到");
    }
}