package com.hanhong.sycamore.modules.system.controller;
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
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hanhong.sycamore.common.util.Result;
import com.hanhong.sycamore.modules.system.entity.Employee;
import com.hanhong.sycamore.modules.system.service.EmployeeServiceInter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "人员管理", description = "人员管理接口")
@RestController
@RequestMapping("/staffs")
public class EmployeeController {

    @Autowired
    private EmployeeServiceInter employeeService;

    @Operation(summary = "分页查询人员")
    @GetMapping
    public Result<Page<Employee>> list(
            @RequestParam(required = false) Long shopId,
            @RequestParam(required = false) Integer status,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        if (shopId != null) {
            wrapper.eq(Employee::getShopId, shopId);
        }
        if (status != null) {
            wrapper.eq(Employee::getStatus, status);
        }
        if (keyword != null && !keyword.isEmpty()) {
            wrapper.and(w -> w.like(Employee::getName, keyword)
                    .or().like(Employee::getPhone, keyword)
                    .or().like(Employee::getEmpNo, keyword));
        }
        wrapper.orderByDesc(Employee::getCreatedAt);
        
        Page<Employee> result = employeeService.page(new Page<>(page, size), wrapper);
        return Result.success(result);
    }

    @Operation(summary = "根据ID查询人员")
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    @Operation(summary = "创建人员")
    @PostMapping
    public Result<Employee> create(@RequestBody Employee employee) {
        employeeService.save(employee);
        return Result.success(employee);
    }

    @Operation(summary = "更新人员")
    @PutMapping("/{id}")
    public Result<Employee> update(@PathVariable Long id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.updateById(employee);
        return Result.success(employee);
    }

    @Operation(summary = "删除人员")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        employeeService.removeById(id);
        return Result.success();
    }
}
