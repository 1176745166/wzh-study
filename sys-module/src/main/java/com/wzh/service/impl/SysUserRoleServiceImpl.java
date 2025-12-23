package com.wzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysUserRole;
import com.wzh.mapper.UserRoleMapper;
import com.wzh.service.ISysUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表 Service 实现类
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, SysUserRole> implements ISysUserRoleService {
}