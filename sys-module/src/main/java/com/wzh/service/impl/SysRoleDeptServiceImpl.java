package com.wzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysRoleDept;
import com.wzh.mapper.RoleDeptMapper;
import com.wzh.service.ISysRoleDeptService;
import org.springframework.stereotype.Service;

/**
 * 角色和部门关联表 Service 实现类
 */
@Service
public class SysRoleDeptServiceImpl extends ServiceImpl<RoleDeptMapper, SysRoleDept> implements ISysRoleDeptService {
}