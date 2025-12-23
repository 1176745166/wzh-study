package com.wzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysUser;
import com.wzh.mapper.UserMapper;
import com.wzh.service.ISysUserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息表 Service 实现类
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<UserMapper, SysUser> implements ISysUserService {
}