package com.demo;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.demo.controller.interceptor.AuthHandlerInterceptor;
import com.demo.utils.TokenUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//开启读取yml配置
//@EnableConfigurationProperties(TokenUtil.class)
//开启事务管理
@EnableTransactionManagement
//开启jetcache缓存
@EnableCreateCacheAnnotation
//开启jetcache中方法的缓存
@EnableMethodCache(basePackages = "com.demo")
public class Ssmp03Application {

    public static void main(String[] args) {
        SpringApplication.run(Ssmp03Application.class, args);
    }

}
