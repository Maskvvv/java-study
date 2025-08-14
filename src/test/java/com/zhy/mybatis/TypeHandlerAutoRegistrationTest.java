package com.zhy.mybatis;

import com.zhy.mybatis.config.MybatisConfig;
import com.zhy.mybatis.enums.DeleteStatus;
import com.zhy.mybatis.enums.UserStatus;
import com.zhy.mybatis.handler.BaseEnumTypeHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 枚举TypeHandler自动注册测试
 * 
 * @author zhy
 * @since 2025-08-12
 */
@SpringBootTest
@TestPropertySource(properties = {
    "spring.datasource.url=jdbc:h2:mem:testdb",
    "spring.datasource.driver-class-name=org.h2.Driver",
    "spring.datasource.username=sa",
    "spring.datasource.password=",
    "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class TypeHandlerAutoRegistrationTest {

    @Autowired
    private org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory;

    /**
     * 测试枚举TypeHandler是否自动注册成功
     */
    @Test
    public void testEnumTypeHandlerAutoRegistration() {
        Configuration configuration = sqlSessionFactory.getConfiguration();
        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        
        // 验证UserStatus枚举的TypeHandler是否已注册
        assertNotNull(typeHandlerRegistry.getTypeHandler(UserStatus.class), 
            "UserStatus枚举的TypeHandler应该已自动注册");
        
        // 验证DeleteStatus枚举的TypeHandler是否已注册
        assertNotNull(typeHandlerRegistry.getTypeHandler(DeleteStatus.class), 
            "DeleteStatus枚举的TypeHandler应该已自动注册");
        
        // 验证注册的TypeHandler是BaseEnumTypeHandler类型
        assertTrue(typeHandlerRegistry.getTypeHandler(UserStatus.class) instanceof BaseEnumTypeHandler, 
            "UserStatus的TypeHandler应该是BaseEnumTypeHandler类型");
        
        assertTrue(typeHandlerRegistry.getTypeHandler(DeleteStatus.class) instanceof BaseEnumTypeHandler, 
            "DeleteStatus的TypeHandler应该是BaseEnumTypeHandler类型");
    }

    /**
     * 测试枚举值转换功能
     */
    @Test
    public void testEnumValueConversion() {
        // 测试UserStatus枚举值
        assertEquals(0, UserStatus.DISABLED.getCode());
        assertEquals(1, UserStatus.ENABLED.getCode());
        assertEquals("禁用", UserStatus.DISABLED.getDescription());
        assertEquals("启用", UserStatus.ENABLED.getDescription());
        
        // 测试DeleteStatus枚举值
        assertEquals(0, DeleteStatus.NOT_DELETED.getCode());
        assertEquals(1, DeleteStatus.DELETED.getCode());
        assertEquals("未删除", DeleteStatus.NOT_DELETED.getDescription());
        assertEquals("已删除", DeleteStatus.DELETED.getDescription());
    }
}