package com.wzh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wzh.domain.SysRole;
import com.wzh.domain.SysUser;

import java.util.List;
import java.util.Set;

/**
 * 角色信息表 Service 接口
 */
public interface ISysRoleService extends IService<SysRole> {

      List<SysRole> getRolesByUserId(Long userId);

      Set<String> selectRolePermissionByUserId(Long userId);

}