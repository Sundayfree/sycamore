package com.hanhong.sycamore.modules.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanhong.sycamore.modules.system.entity.Role;
import com.hanhong.sycamore.modules.system.mapper.RoleMapper;
import com.hanhong.sycamore.modules.system.service.RoleServiceInter;
import org.springframework.stereotype.Service;

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

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleServiceInter {
}