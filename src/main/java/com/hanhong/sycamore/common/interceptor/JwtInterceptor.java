package com.hanhong.sycamore.common.interceptor;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanhong.sycamore.common.util.JwtUtil;
import com.hanhong.sycamore.common.util.UserContext;
import com.hanhong.sycamore.modules.auth.entity.UserToken;
import com.hanhong.sycamore.modules.auth.mapper.UserTokenMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

/**
 * Sycamore Restaurant System
 * <p>
 * Copyright (c) 2026 sycamore team. All rights reserved.
 * <p>
 * 免责声明：
 * 本代码仅供内部开发与学习使用，未经授权不得外传或用于商业用途。
 *
 * @author Liu qingyang
 * @version 1.0.0
 * @since 2026-04
 */

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private UserTokenMapper userTokenMapper;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        String auth = request.getHeader("Authorization");

        if (auth == null || auth.isBlank()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":401,\"message\":\"未登录\"}");
            return false;
        }

        String token = auth.startsWith("Bearer ")
                ? auth.substring(7)
                : auth;

        try {
            Claims claims = JwtUtil.parseToken(token);

            UserToken dbToken = userTokenMapper.selectOne(
                    new LambdaQueryWrapper<UserToken>()
                            .eq(UserToken::getToken, token)
            );

            if (dbToken == null) {
                response.setStatus(401);
                response.getWriter().write("token不存在");
                return false;
            }


            if (dbToken.getExpireTime().isBefore(LocalDateTime.now())) {
                response.setStatus(401);
                response.getWriter().write("token过期");
                return false;
            }

            UserContext.set(claims);

            return true;

        } catch (Exception e) {
            response.setStatus(401);
            response.getWriter().write("Unexpected error");
            return false;
        }
    }
}