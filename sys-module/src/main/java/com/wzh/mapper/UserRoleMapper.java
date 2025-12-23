package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表 Mapper 接口
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<SysUserRole> {
}