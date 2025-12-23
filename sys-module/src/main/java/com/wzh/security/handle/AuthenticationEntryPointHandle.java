package com.wzh.security.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wzh.vo.ResponseVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @Auther: wzh
 * @Date: 2025/12/23 - 12 - 23 - 22:04
 * @Description: com.wzh.security.handle
 * @version: 1.0
 */
@Component
public class AuthenticationEntryPointHandle implements AuthenticationEntryPoint {


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");

        String code = String.valueOf(HttpStatus.UNAUTHORIZED);
        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());

        ResponseVO responseVO = ResponseVO.error(Integer.valueOf(code), msg);
        String json = objectMapper.writeValueAsString(responseVO);

        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}