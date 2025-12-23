package com.wzh.security.handle;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.wzh.constant.Constants;
import com.wzh.security.service.TokenService;
import com.wzh.utils.MessageUtils;
import com.wzh.vo.ResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 22:15
 * @Description: 登出成功处理类
 * @version: 1.0
 */
@Component
@AllArgsConstructor
public class LogoutSuccessToHandler implements LogoutSuccessHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();


    private final TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(HttpStatus.HTTP_OK);
        response.setContentType("application/json;charset=utf-8");

        // 获取请求头中的token
        String token = request.getHeader(Constants.TOKEN_PREFIX);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            // 删除Redis中的用户信息
            tokenService.delLoginUser(token);
        }

        ResponseVO responseVO = ResponseVO.success(MessageUtils.message("user.logout.success"));
        String json = objectMapper.writeValueAsString(responseVO);

        response.getWriter().write(json);
    }
}
