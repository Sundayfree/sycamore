package com.hanhong.sycamore.common.vo;


import lombok.Data;

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

@Data
public class LoggedUser {
    private Long userId;
    private String username;
    private String realName;
    private String role;       // KDS / CASHIER / ADMIN
    private Long shopId;
    private LocalDateTime loginAt;
}
