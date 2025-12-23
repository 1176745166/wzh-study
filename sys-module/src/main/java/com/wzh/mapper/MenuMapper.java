package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 菜单权限表 Mapper 接口
 */
@Mapper
public interface MenuMapper extends BaseMapper<SysMenu> {
}