package com.wzh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzh.domain.SysDept;
import com.wzh.mapper.DeptMapper;
import com.wzh.service.ISysDeptService;
import org.springframework.stereotype.Service;

/**
 * 部门表 Service 实现类
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<DeptMapper, SysDept> implements ISysDeptService {
}