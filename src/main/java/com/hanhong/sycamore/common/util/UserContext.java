package com.hanhong.sycamore.common.util;


import io.jsonwebtoken.Claims;

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

public class UserContext {
    private static final ThreadLocal<Claims> TL = new ThreadLocal<>();

    public static void set(Claims claims) {
        TL.set(claims);
    }

    public static Claims get() {
        return TL.get();
    }

    public static void clear() {
        TL.remove();
    }
}
