package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 角色和菜单关联表 Mapper 接口
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<SysRoleMenu> {
}