package com.wzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysRoleMenu;
import com.wzh.mapper.RoleMenuMapper;
import com.wzh.service.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表 Service 实现类
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {
}