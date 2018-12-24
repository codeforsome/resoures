package com.example.health.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


//处理首页和退出请求
@Controller
@RequestMapping("/admin/index/")
public class AdminIndexController {
    @RequestMapping("")
    String getHtml(){
        return "adminhtml/index";
    }

    /*** 注销登录*/
    @GetMapping("loginout")
    public String  loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "adminhtml/login";
    }
}
