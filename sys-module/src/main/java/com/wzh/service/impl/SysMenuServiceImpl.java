package com.wzh.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysMenu;
import com.wzh.domain.SysRoleMenu;
import com.wzh.mapper.MenuMapper;
import com.wzh.mapper.RoleMenuMapper;
import com.wzh.service.ISysMenuService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 菜单权限表 Service 实现类
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<MenuMapper, SysMenu> implements ISysMenuService {

    private final RoleMenuMapper roleMenuMapper;
    private final MenuMapper menuMapper;

    //获取某个角色的菜单权限
    @Override
    public Set<String> getMenuPermsByRoleId(Long roleId) {
        // 1. 先查询角色拥有的菜单ID
        List<Long> menuIds = roleMenuMapper.selectObjs(
                        Wrappers.<SysRoleMenu>lambdaQuery()
                                .select(SysRoleMenu::getMenuId)
                                .eq(SysRoleMenu::getRoleId, roleId)
                ).stream()
                .map(obj -> (Long) obj)
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(menuIds)) {
            return Collections.emptySet();
        }

        // 2. 查询菜单权限
        List<String> perms = menuMapper.selectObjs(
                        Wrappers.<SysMenu>lambdaQuery()
                                .select(SysMenu::getPerms)
                                .eq(SysMenu::getStatus, "0")
                                .in(SysMenu::getId, menuIds)
                ).stream()
                .map(obj -> (String) obj)
                .distinct()  // 去重
                .collect(Collectors.toList());

        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}