package com.wzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.wzh.mapper") // 扫描 Mapper 接口
public class SysModuleApplication
{
    public static void main( String[] args )
    {
        ConfigurableApplicationContext context = SpringApplication.run(SysModuleApplication.class, args);
        //验证容器是否有AuthenticationManager
        System.out.println(context.containsBean("AuthenticationManager"));
    }
}