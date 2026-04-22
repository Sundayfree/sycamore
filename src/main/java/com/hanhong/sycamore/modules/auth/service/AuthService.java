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
import com.hanhong.sycamore.common.util.General;
import com.hanhong.sycamore.common.util.JwtUtil;
import com.hanhong.sycamore.common.dto.LoggedUser;
import com.hanhong.sycamore.modules.auth.entity.UserToken;
import com.hanhong.sycamore.modules.auth.mapper.UserTokenMapper;
import com.hanhong.sycamore.modules.system.entity.Employee;
import com.hanhong.sycamore.modules.system.mapper.EmployeeMapper;
import io.jsonwebtoken.Claims;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;


@Service
public class AuthService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private UserTokenMapper userTokenMapper;

    public LoginResult login(String username, String password) {

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return null;
        }

        // 查询员工
        Employee employee = employeeMapper.selectOne(
                new LambdaQueryWrapper<Employee>()
                        .eq(Employee::getEmpNo, username)
        );

        if (employee == null) {
            return null;
        }

        if (employee.getStatus() == 0) {
            return null;
        }

        if (!General.matchPassword(password, employee.getPasswordHash())) {
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
                employee.getFullname()
        );

        UserToken userToken = new UserToken();
        userToken.setEmployeeId(employee.getId());
        userToken.setShopId(employee.getShopId());
        userToken.setToken(token);
        userToken.setExpireTime(LocalDateTime.now().plusDays(1));

        userTokenMapper.delete(new LambdaQueryWrapper<UserToken>()
                .eq(UserToken::getEmployeeId, employee.getId()));

        userTokenMapper.insert(userToken);

        // 返回用户信息
        LoggedUser user = new LoggedUser();
        user.setUserId(employee.getId());
        user.setUsername(employee.getFullname());
        user.setRealName("系统管理员");
        user.setRole(role);
        user.setShopId(employee.getShopId());
        user.setLoginAt(LocalDateTime.now());

        return new LoginResult(token, user);
    }

    /**
     * logout
     */
    public void logout(String auth) {
        if (auth == null || auth.isBlank()) {
            return;
        }

        String token = auth.startsWith("Bearer ")
                ? auth.substring(7)
                : auth;

        userTokenMapper.delete(
                new LambdaQueryWrapper<UserToken>()
                        .eq(UserToken::getToken, token)
        );
    }

    // ============================
    // DTO
    // ============================

    @Data
    public static class LoginResult {
        private final String token;
        private final LoggedUser user;
    }
}
