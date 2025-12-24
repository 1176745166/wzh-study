package com.wzh.security.dto.login;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 16:51
 * @Description: com.wzh.dto
 * @version: 1.0
 */
@Data
public class LoginDTO {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 用户密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

//    /**
//     * 验证码
//     */
//    @NotBlank(message = "验证码不能为空")
//    private String code;
}
