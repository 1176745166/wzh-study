package com.wzh.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysRole;
import com.wzh.domain.SysUserRole;
import com.wzh.mapper.RoleMapper;
import com.wzh.mapper.UserRoleMapper;
import com.wzh.service.ISysRoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色信息表 Service 实现类
 */
@Service
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<RoleMapper, SysRole> implements ISysRoleService {

    private final UserRoleMapper userRoleMapper;
    private final RoleMapper roleMapper;

    /*
     * 根据用户id查询对应的角色列表
     */
    @Override
    public List<SysRole> getRolesByUserId(Long userId) {
        // 1. 查询用户角色关联表，获取角色ID列表
        List<SysUserRole> userRoles = userRoleMapper.selectList(
                Wrappers.<SysUserRole>lambdaQuery()
                        .eq(SysUserRole::getUserId, userId)
        );

        if (CollectionUtils.isEmpty(userRoles)) {
            return Collections.emptyList();
        }

        // 2. 提取角色ID
        List<Long> roleIds = userRoles.stream()
                .map(SysUserRole::getRoleId)
                .collect(Collectors.toList());

        // 3. 查询角色表
        return roleMapper.selectBatchIds(roleIds);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId)
    {
        List<SysRole> perms =getRolesByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (SysRole perm : perms)
        {
            if (ObjectUtil.isNotNull(perm))
            {
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}