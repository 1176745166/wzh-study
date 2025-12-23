package com.wzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzh.domain.SysMenu;

import java.util.List;
import java.util.Set;

/**
 * 菜单权限表 Service 接口
 */
public interface ISysMenuService extends IService<SysMenu> {
     Set<String> getMenuPermsByRoleId(Long roleId);
}