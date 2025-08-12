package com.zhy.mybatis;

import com.zhy.mybatis.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 简单的用户测试类 - 不依赖Spring上下文
 * 
 * @author zhy
 * @since 2025-08-11
 */
public class SimpleUserTest {

    @Test
    public void testUserEntity() {
        // 测试用户实体类的基本功能
        User user = new User()
                .setUsername("testuser")
                .setEmail("test@example.com")
                .setAge(25)
                .setStatus(1);

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals(25, user.getAge());
        assertEquals(1, user.getStatus());
    }

    @Test
    public void testUserValidation() {
        // 测试用户数据验证
        User user = new User();
        
        // 测试空值
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getAge());
        
        // 测试链式调用
        user.setUsername("test")
            .setEmail("test@test.com")
            .setAge(30);
            
        assertEquals("test", user.getUsername());
        assertEquals("test@test.com", user.getEmail());
        assertEquals(30, user.getAge());
    }

    @Test
    public void testUserBuilder() {
        // 测试用户构建
        User user1 = new User();
        user1.setId(1L)
             .setUsername("user1")
             .setEmail("user1@test.com")
             .setAge(20)
             .setStatus(1)
             .setDeleted(0);

        User user2 = new User()
                .setId(2L)
                .setUsername("user2")
                .setEmail("user2@test.com")
                .setAge(30)
                .setStatus(0)
                .setDeleted(0);

        assertNotEquals(user1.getId(), user2.getId());
        assertNotEquals(user1.getUsername(), user2.getUsername());
        assertNotEquals(user1.getAge(), user2.getAge());
        assertNotEquals(user1.getStatus(), user2.getStatus());
    }
}