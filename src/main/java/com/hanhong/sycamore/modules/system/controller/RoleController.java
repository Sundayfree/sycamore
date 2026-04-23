package com.hanhong.sycamore.modules.system.controller;


import com.hanhong.sycamore.common.util.Result;
import com.hanhong.sycamore.modules.system.entity.Role;
import com.hanhong.sycamore.modules.system.service.RoleServiceInter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServiceInter roleService;

    @GetMapping("/list")
    public Result<List<Role>> list() {
        return Result.success(roleService.list());
    }

    @GetMapping("/{id}")
    public Result<Role> detail(@PathVariable Long id) {
        Role role = roleService.getById(id);
        return role != null
                ? Result.success(role)
                : Result.error("Role not found");
    }

    @PostMapping("/create")
    public Result<Boolean> create(@RequestBody Role role) {
        boolean saved = roleService.save(role);
        return saved
                ? Result.success(true)
                : Result.error("Create role failed");
    }

    @PutMapping("/update/{id}")
    public Result<Boolean> update(@PathVariable Long id,
                                  @RequestBody Role role) {

        role.setId(id);
        boolean updated = roleService.updateById(role);

        return updated
                ? Result.success(true)
                : Result.error("Update role failed");
    }

    @DeleteMapping("/delete/{id}")
    public Result<Boolean> delete(@PathVariable Long id) {

        boolean removed = roleService.removeById(id);

        return removed
                ? Result.success(true)
                : Result.error("Delete role failed");
    }
}
