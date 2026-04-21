package com.hanhong.sycamore.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanhong.sycamore.system.entity.Employee;
import com.hanhong.sycamore.system.service.EmployeeServiceInter;
import com.hanhong.sycamore.system.mapper.EmployeeMapper;
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
