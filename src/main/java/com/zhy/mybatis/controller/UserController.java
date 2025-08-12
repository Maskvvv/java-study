package com.zhy.mybatis.controller;

import com.zhy.mybatis.entity.User;
import com.zhy.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * mybatis测试
 * 
 * @author zhy
 * @since 2025-08-11
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     * 
     * @return 用户列表
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.list();
        return ResponseEntity.ok(users);
    }

    /**
     * 根据ID获取用户
     * 
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return Optional.ofNullable(userService.getById(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    /**
     * 根据年龄范围获取用户列表
     * 
     * @param minAge 最小年龄
     * @param maxAge 最大年龄
     * @return 用户列表
     */
    @GetMapping("/age")
    public ResponseEntity<List<User>> getUsersByAgeRange(
            @RequestParam Integer minAge, 
            @RequestParam Integer maxAge) {
        List<User> users = userService.findByAgeRange(minAge, maxAge);
        return ResponseEntity.ok(users);
    }

    /**
     * 创建用户
     * 
     * @param user 用户信息
     * @return 创建结果
     */
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        boolean success = userService.createUser(user);
        return success ? 
                ResponseEntity.ok("用户创建成功") : 
                ResponseEntity.badRequest().body("用户创建失败");
    }

    /**
     * 更新用户
     * 
     * @param id 用户ID
     * @param user 用户信息
     * @return 更新结果
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        boolean success = userService.updateUser(user);
        return success ? 
                ResponseEntity.ok("用户更新成功") : 
                ResponseEntity.badRequest().body("用户更新失败");
    }

    /**
     * 删除用户
     * 
     * @param id 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean success = userService.deleteUser(id);
        return success ? 
                ResponseEntity.ok("用户删除成功") : 
                ResponseEntity.badRequest().body("用户删除失败");
    }
}