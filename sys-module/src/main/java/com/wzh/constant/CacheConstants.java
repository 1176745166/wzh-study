package com.wzh.constant;

import io.jsonwebtoken.Claims;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 19:29
 * @Description: 缓存的key 常量
 * @version: 1.0
 */
public class CacheConstants {

    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 登录账户密码错误次数 redis key
     */
    public static final String PWD_ERR_CNT_KEY = "pwd_err_cnt:";

}
