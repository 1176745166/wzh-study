package com.wzh.security.context;

import org.springframework.security.core.Authentication;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 20:14
 * @Description: 身份验证信息
 * @version: 1.0
 */
public class AuthenticationContextHolder
{
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext()
    {
        return contextHolder.get();
    }

    public static void setContext(Authentication context)
    {
        contextHolder.set(context);
    }

    public static void clearContext()
    {
        contextHolder.remove();
    }
}
