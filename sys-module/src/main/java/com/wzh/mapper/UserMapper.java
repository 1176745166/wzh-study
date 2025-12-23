package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息表 Mapper 接口
 */
@Mapper
public interface UserMapper extends BaseMapper<SysUser> {
}