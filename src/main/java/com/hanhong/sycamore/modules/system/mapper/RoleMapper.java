package com.hanhong.sycamore.modules.system.mapper;


import com.hanhong.sycamore.modules.system.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


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

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
