package com.hanhong.sycamore.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanhong.sycamore.system.entity.Employee;

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
}
