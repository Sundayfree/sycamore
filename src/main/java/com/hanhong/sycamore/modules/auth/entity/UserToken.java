package com.hanhong.sycamore.modules.auth.entity;


import com.baomidou.mybatisplus.annotation.*;
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
@TableName("user_token")
public class UserToken {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long employeeId;
    private Long shopId;
    private String token;

    private LocalDateTime expireTime;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
