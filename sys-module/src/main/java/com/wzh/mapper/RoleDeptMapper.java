package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysRoleDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和部门关联表 Mapper 接口
 */
@Mapper
public interface RoleDeptMapper extends BaseMapper<SysRoleDept> {
}