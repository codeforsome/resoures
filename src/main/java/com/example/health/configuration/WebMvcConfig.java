package com.example.health.configuration;

import org.springframework.context.annotation.Configuration;import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//把本地的绝对路径加到spring boot的静态资源路径里，作为资源服务器使用
@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/public/image/**").addResourceLocations("file:G:\\images\\");
//                "E:\\SpringWeb\\health\\src\\main\\resources\\static\\article\\images\\"
        super.addResourceHandlers(registry);
    }
}
