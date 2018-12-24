package com.example.health.configuration;

import com.example.health.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        InterceptorRegistration loginRegistry = registry.addInterceptor(loginInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/admin/**");
        // 排除路径
        loginRegistry.excludePathPatterns("/admin/login");
        loginRegistry.excludePathPatterns("/admin/verify");
        // 排除资源请求
        loginRegistry.excludePathPatterns("/css/*.css");
        loginRegistry.excludePathPatterns("/js/**/*.js");
        loginRegistry.excludePathPatterns("/image/*.png");
    }
}