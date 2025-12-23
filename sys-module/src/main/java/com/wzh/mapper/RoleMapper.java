package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色信息表 Mapper 接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<SysRole> {
}