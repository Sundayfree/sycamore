package com.hanhong.sycamore.modules.system.service.impl;
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
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanhong.sycamore.modules.system.entity.Employee;
import com.hanhong.sycamore.modules.system.service.EmployeeServiceInter;
import com.hanhong.sycamore.modules.system.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
 * 系统用户Service实现
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeServiceInter {

    @Override
    public IPage<Employee> getUserList(String keyword, Integer roleId, String status, Integer page, Integer size) {
        // TODO rework pagination
        return new Page<>(page != null ? page : 1, size != null ? size : 20);
    }

    @Override
    public Employee getUserDetail(Long userId) {
        return getById(userId);
    }

    @Override
    public Employee createUser(Employee user) {
        user.setStatus(1);
        save(user);
        return user;
    }

    @Override
    public boolean updateUser(Long userId, Employee user) {
        user.setId(userId);
        return updateById(user);
    }
}
