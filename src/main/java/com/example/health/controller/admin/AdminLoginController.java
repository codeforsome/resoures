package com.example.health.controller.admin;

import com.example.health.mapper.AdminMapper;
import com.example.health.mapper.UserInfoMapper;
import com.example.health.model.ResponseData;
import com.example.health.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

//处理登陆请求
@Controller
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    AdminMapper adminMapper;

    //返回登陆界面
    @GetMapping("/login")
    public String getLogin(){
        return "adminhtml/login";
    }

    //验证登陆信息
    @ResponseBody
    @PostMapping("/verify")
    public ResponseData verifyUsername(@RequestBody User user,
                                 HttpServletRequest request) {
        User admin=adminMapper.selectIsEntry(user);
        if(admin==null){
            return new ResponseData(false,"用户或者密码错误",null);
        }else {
            // 校验通过时，在session里放入一个标识
            // 后续通过session里是否存在该标识来判断用户是否登录
            request.getSession().setAttribute("loginName", "admin");
            return new ResponseData(true,"成功",null);
        }
    }


}
