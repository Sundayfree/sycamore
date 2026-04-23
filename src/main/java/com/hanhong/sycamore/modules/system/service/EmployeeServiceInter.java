package com.hanhong.sycamore.modules.system.service;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanhong.sycamore.modules.system.entity.Employee;

/**
 * 系统用户Service
 */
public interface EmployeeServiceInter extends IService<Employee> {

    /**
     * 获取用户列表（分页）
     */
    IPage<Employee> getUserList(String keyword, Integer roleId, String status, Integer page, Integer size);

    /**
     * 获取用户详情
     */
    Employee getUserDetail(Long userId);

    /**
     * 创建用户
     */
    Employee createUser(Employee user);

    /**
     * 更新用户
     */
    boolean updateUser(Long userId, Employee user);

    boolean assignRole(Long employeeId, Long roleId);
}
