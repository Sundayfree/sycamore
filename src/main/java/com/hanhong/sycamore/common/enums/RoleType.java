package com.hanhong.sycamore.common.enums;


import lombok.Getter;

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

public enum RoleType {

    BOSS(1L, "boss"),
    ADMIN(2L, "admin"),
    MANAGER(3L, "manager"),
    CASHIER(4L, "cashier"),
    CHEF(5L, "chef"),
    STAFF(null, "staff");

    private final Long code;
    @Getter
    private final String role;

    RoleType(Long code, String role) {
        this.code = code;
        this.role = role;
    }

    public static String from(Long roleId) {
        if (roleId == null) {
            return STAFF.role;
        }

        for (RoleType r : values()) {
            if (r.code != null && r.code.equals(roleId)) {
                return r.role;
            }
        }
        return STAFF.role;
    }
}