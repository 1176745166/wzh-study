package com.wzh.constant;

import io.jsonwebtoken.Claims;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 19:22
 * @Description: 通用常量信息
 * @version: 1.0
 */
public class Constants {

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key";

    /**
     * 用户名称
     */
    public static final String JWT_USERNAME = Claims.SUBJECT;
}
