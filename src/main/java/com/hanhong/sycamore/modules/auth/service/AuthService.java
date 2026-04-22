package com.hanhong.sycamore.modules.auth.service;

/**
 * Sycamore Restaurant System
 *
 * Copyright (c) 2026 sycamore team. All rights reserved.
 *
 * 免责声明：
 * 本代码仅供内部开发与学习使用，未经授权不得外传或用于商业用途。
 *
 * @author Liu qingyang
 * @version 1.0.0
 * @since 2026-04
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanhong.sycamore.common.enums.RoleType;
import com.hanhong.sycamore.common.util.JwtUtil;
import com.hanhong.sycamore.common.vo.LoggedUser;
import com.hanhong.sycamore.modules.system.entity.Employee;
import com.hanhong.sycamore.modules.system.mapper.EmployeeMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录认证服务
 *
 * 支持两种模式：
 * 1. 演示模式：admin/admin456 直接登录
 * 2. 正式模式：从数据库验证用户
 *
 * 业务规则：
 * - 店长/店员登录 → 自动设置为用户所属门店的shopId，且不允许切换
 * - 老板/管理员登录 → 可以选择门店，默认设置一个shopId
 *
 * Token 格式：Bearer <uuid>（简化版 UUID token）
 */
@Service
public class AuthService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public LoginResult login(String username, String password) {

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        // 查询员工
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getName, username);
        wrapper.eq(Employee::getPasswordHash, password);

        Employee employee = employeeMapper.selectOne(wrapper);

        if (employee == null) {
            return null;
        }

        if (employee.getStatus() == 0) {
            return null;
        }

        // 角色
        String role = RoleType.from(employee.getRoleId());

        // 🔥 生成 JWT
        String token = JwtUtil.generateToken(
                employee.getId(),
                role,
                employee.getShopId(),
                employee.getName()
        );

        // 返回用户信息
        LoggedUser user = new LoggedUser();
        user.setUserId(employee.getId());
        user.setUsername(employee.getName());
        user.setRealName("系统管理员");
        user.setRole(role);
        user.setShopId(employee.getShopId());
        user.setLoginAt(LocalDateTime.now());

        return new LoginResult(token, user);
    }

//    /**
//     * 登出
//     */
//    public void logout(String token) {
//        if (token != null && token.startsWith("Bearer ")) {
//            token = token.substring(7);
//        }
//        tokenStore.remove(token);
//    }

    // ============================
    // DTO
    // ============================

    @Data
    public static class LoginResult {
        private final String token;
        private final LoggedUser user;
    }
}
