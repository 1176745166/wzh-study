package com.wzh;

import com.wzh.domain.SysUser;
import com.wzh.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;

/**
 * @Auther: wzh
 * @Date: 2025/12/24 - 12 - 24 - 10:51
 * @Description: com.wzh
 * @version: 1.0
 */
@SpringBootTest(
    classes = SysModuleApplication.class,
    properties = {
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration,org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration"
    }
)
@TestPropertySource(properties = {
    "token.header=Authorization",
    "token.secret=abcdefghijklmnopqrstuvwxyz0123456789ABCD",
    "token.expireTime=30",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.datasource.url=jdbc:mysql://localhost:3306/sys-module?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true",
    "spring.datasource.username=root",
    "spring.datasource.password=123456",
    "spring.data.redis.host=localhost",
    "spring.data.redis.port=6379"
})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 使用实际数据库
public class SysModuleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsertUser(){
        SysUser sysUser = new SysUser();
        sysUser.setUserName("wzh");
        sysUser.setNickName("wzh");
        sysUser.setPassword("123456");
        sysUser.setDeptId(103L);
        sysUser.setEmail("wzh@qq.com");
        sysUser.setPhoneNumber("12345678901");
        sysUser.setStatus("0");
        sysUser.setCreateBy("wzh");
        sysUser.setUpdateBy("wzh");
        userMapper.insert(sysUser);
    }
}