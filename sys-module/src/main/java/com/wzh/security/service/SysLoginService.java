package com.wzh.security.service;

import com.exception.user.UserNotExistsException;
import com.exception.user.UserPasswordNotMatchException;
import com.redis.RedisCache;
import com.wzh.security.dto.login.LoginUser;
import com.wzh.constant.UserConstants;
import com.wzh.security.context.AuthenticationContextHolder;
import com.wzh.service.ISysUserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 17:14
 * @Description: 登录校验的service
 * @version: 1.0
 */
@Component
@AllArgsConstructor
public class SysLoginService {

    private final TokenService tokenService;

    //认证管理器
    private final  AuthenticationManager authenticationManager;

    private final RedisCache redisCache;

    private final ISysUserService userService;


    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果
     */

    public String login(String username, String password) {
       //1.验证码校验 TODO

       //2.登录前置校验
        loginPreCheck(username, password);
       //3.用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){

                throw new UserPasswordNotMatchException();
        }finally {
            AuthenticationContextHolder.clearContext();
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    /**
     * 登录前置校验
     * @param username 用户名
     * @param password 用户密码
     */
    public void loginPreCheck(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password))
        {
            throw new UserNotExistsException();
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new UserPasswordNotMatchException();
        }
    }
}
