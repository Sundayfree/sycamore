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

package com.hanhong.sycamore.modules.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.hanhong.sycamore.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 员工表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("employee")
public class Employee extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long shopId;

    private String empNo;

    private String fullname;

    private Integer gender;

    private String phone;

    private String idCard;

    private Long roleId;

    private String passwordHash;

    private String avatarUrl;

    private Integer status;

    private LocalDate hireDate;

    private LocalDate leaveDate;

    private Integer deleted;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
