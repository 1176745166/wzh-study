package com.wzh.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户和角色关联 sys_user_role
 */
@Data
@TableName("sys_user_role")
@AllArgsConstructor
@NoArgsConstructor
public class SysUserRole
{
    /** 用户ID */
    private Long userId;
    
    /** 角色ID */
    private Long roleId;


}
