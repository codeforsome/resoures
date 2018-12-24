package com.example.health.configuration;

import com.example.health.interceptor.LoginInterceptor;
import com.example.health.interceptor.UserViewInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UserViewConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        UserViewInterceptor userViewInterceptor = new UserViewInterceptor();
        InterceptorRegistration userViewRegistry = registry.addInterceptor(userViewInterceptor);
        // 拦截路径
        userViewRegistry.addPathPatterns("/user/**");
//        // 排除资源请求
//        userViewRegistry.excludePathPatterns("/css/*.css");
//        userViewRegistry.excludePathPatterns("/js/**/*.js");
//        userViewRegistry.excludePathPatterns("/image/*.png");
    }
}
