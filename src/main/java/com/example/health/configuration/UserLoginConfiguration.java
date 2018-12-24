//package com.example.health.configuration;
//
//import com.example.health.interceptor.UserLoginInterceptor;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class UserLoginConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册拦截器
//        UserLoginInterceptor userLoginInterceptor = new UserLoginInterceptor();
//        InterceptorRegistration userLoginRegistry = registry.addInterceptor(userLoginInterceptor);
//        // 拦截路径
//        userLoginRegistry.addPathPatterns("/login/verify");
//        userLoginRegistry.addPathPatterns("/register/register-user");
//
//    }
//}
