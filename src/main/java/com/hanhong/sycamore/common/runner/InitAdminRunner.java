package com.hanhong.sycamore.common.runner;


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

import com.hanhong.sycamore.system.entity.Employee;
import com.hanhong.sycamore.system.service.impl.EmployeeServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class InitAdminRunner implements ApplicationRunner {

    @Resource
    private EmployeeServiceImpl employeeService;

    @Override
    public void run(ApplicationArguments args) {
        if (employeeService.lambdaQuery().count() > 0) {
            System.out.println("⚠️ 已存在 admin，跳过初始化");
            return;
        }

        Employee admin = new Employee();
        admin.setShopId(1L);
        admin.setEmpNo("admin");
        admin.setName("Super Admin");
        admin.setGender(1);
        admin.setPhone("00000000000");
        admin.setRoleId(1L);
        admin.setStatus(1);
        admin.setHireDate(LocalDate.now());
        admin.setDeleted(0);
        admin.setCreatedAt(LocalDateTime.now());
        admin.setUpdatedAt(LocalDateTime.now());

        admin.setPasswordHash("123456");
        employeeService.save(admin);
    }
}