package com.wzh.security.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.exception.service.ServiceException;
import com.wzh.domain.SysUser;
import com.wzh.security.dto.login.LoginUser;
import com.wzh.service.ISysUserService;
import com.wzh.utils.MessageUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * 用户验证处理
 *
 * @author wzh
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService
{
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    private final ISysUserService userService;


    private final SysPasswordService passwordService;


    private SysPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        //根据用户名获取用户
        SysUser user = userService.getBaseMapper().selectOne(Wrappers.<SysUser>lambdaQuery()
                .eq(SysUser::getUserName, username));
        if (ObjectUtil.isNull(user))
        {
            log.info("登录用户：{} 不存在.", username);
            throw new ServiceException(MessageUtils.message("user.not.exists"));
        }
        else if ("1".equals(user.getDelFlag()))
        {
            log.info("登录用户：{} 已被删除.", username);
            throw new ServiceException(MessageUtils.message("user.password.delete"));
        }
        else if ("1".equals(user.getStatus()))
        {
            log.info("登录用户：{} 已被停用.", username);
            throw new ServiceException(MessageUtils.message("user.blocked"));
        }

        passwordService.validate(user);

        return createLoginUser(user);
    }

    public UserDetails createLoginUser(SysUser user)
    {
        return new LoginUser(user.getId(), user.getDeptId(), user, permissionService.getMenuPermission(user));
    }
}
