package com.wzh.controller;

import com.wzh.domain.SysUser;
import com.wzh.mapper.UserMapper;
import com.wzh.security.dto.login.LoginDTO;
import com.wzh.security.dto.login.LoginUser;
import com.wzh.security.service.SysPermissionService;
import com.wzh.security.service.TokenService;
import com.wzh.security.utils.SecurityUtils;
import com.wzh.service.ISysMenuService;
import com.wzh.security.service.SysLoginService;
import com.wzh.vo.ResponseVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 16:44
 * @Description: com.wzh.controller
 * @version: 1.0
 */
@RestController
@Tag(name = "登录controller", description = "登录controller")
@RequestMapping("/api")
@AllArgsConstructor
public class SysLoginController {

    private final SysLoginService loginService;

    private final SysPermissionService permissionService;

    private final TokenService tokenService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    @Operation(summary = "登录接口", description = "登录接口")
    public ResponseVO login(@RequestBody LoginDTO loginDTO) {
        String token = loginService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return ResponseVO.success(token).setMessage("登录成功");
    }

    @PostMapping("/getInfo")
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public ResponseVO getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        if (!loginUser.getPermissions().equals(permissions))
        {
            loginUser.setPermissions(permissions);
            tokenService.refreshToken(loginUser);
        }
        // 创建一个Map来封装所有返回的数据
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("user", user);
        userInfo.put("roles", roles);
        userInfo.put("permissions", permissions);

        return ResponseVO.success(userInfo).setMessage("获取用户信息成功");
    }
}
