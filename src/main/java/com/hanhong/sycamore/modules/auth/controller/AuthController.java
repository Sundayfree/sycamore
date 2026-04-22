package com.hanhong.sycamore.modules.auth.controller;

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

import com.hanhong.sycamore.common.util.JwtUtil;
import com.hanhong.sycamore.common.util.Result;
import com.hanhong.sycamore.common.dto.LoggedUser;
import com.hanhong.sycamore.modules.auth.service.AuthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Result<AuthService.LoginResult> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");
        AuthService.LoginResult result = authService.login(username, password);
        if (result != null) {
            return Result.success(result);
        }
        return Result.error("用户名或密码错误");
    }

    @GetMapping("/check")
    public Result<LoggedUser> checkToken(@RequestHeader(value = "Authorization", required = false) String auth) {
        if (auth == null || auth.isBlank()) {
            return Result.error("Token 为空");
        }

        try {
            String token = auth.startsWith("Bearer ")
                    ? auth.substring(7)
                    : auth;

            Claims claims = JwtUtil.parseToken(token);

            Long userId = claims.get("userId", Long.class);
            String role = claims.get("role", String.class);
            Long shopId = claims.get("shopId", Long.class);
            String username = claims.getSubject();

            LoggedUser user = new LoggedUser();
            user.setUserId(userId);
            user.setUsername(username);
            user.setRole(role);
            user.setShopId(shopId);

            return Result.success(user);

        } catch (Exception e) {
            return Result.error("Token 无效或已过期");
        }
    }

    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("Authorization") String auth) {
        authService.logout(auth);
        return Result.success();
    }
}
