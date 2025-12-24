package com.wzh.security.service;

import com.wzh.domain.SysRole;
import com.wzh.domain.SysUser;
import com.wzh.service.ISysMenuService;
import com.wzh.service.ISysRoleService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 20:19
 * @Description: 用户权限处理
 * @version: 1.0
 */
@Component
@AllArgsConstructor
public class SysPermissionService {

    private final ISysRoleService roleService;

    private final ISysMenuService menuService;


    /**
     * 获取角色数据权限
     *
     * @param user 用户信息
     * @return 角色权限信息
     */

    public Set<String> getRolePermission(SysUser user){
        Set<String> roles = new HashSet<>();
        // 管理员拥有所有权限
        if (user.isAdmin()){
            roles.add("admin");
        }else{
            //根据用户id查询权限
            Set<String> perms = roleService.selectRolePermissionByUserId(user.getId());
            roles.addAll(perms);
        }
        return roles;
    }

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user){
        Set<String> perms = new HashSet<String>();
        // 管理员拥有所有权限
        if (user.isAdmin()) {
            perms.add( "*:*:*");
        }else {
            //获取用户的角色列表
            List<SysRole> roles = roleService.getRolesByUserId(user.getId());
            if (!CollectionUtils.isEmpty(roles)){
                // 多角色设置permissions属性，以便数据权限匹配权限
                for (SysRole role : roles)
                {
                    if (StringUtils.equals(role.getStatus(), "0") && !role.isAdmin())
                    {
                        //查询菜单权限
                        Set<String> rolePerms = menuService.getMenuPermsByRoleId(role.getId());
                        role.setPermissions(rolePerms);
                        perms.addAll(rolePerms);
                    }
                }
            }

        }
        return perms;
    }

}
