package com.wzh.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wzh.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 部门表 Mapper 接口
 */
@Mapper
public interface DeptMapper extends BaseMapper<SysDept> {
}