package com.wzh.exception.user;

import com.wzh.exception.BaseException;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 20:09
 * @Description: 用户信息异常类
 * @version: 1.0
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
