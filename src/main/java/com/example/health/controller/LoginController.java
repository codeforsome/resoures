package com.example.health.controller;

import com.example.health.mapper.UserInfoMapper;
import com.example.health.mapper.UserMapper;
import com.example.health.model.ResponseData;
import com.example.health.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("/login")
    public String getLogin(){
//        return "/html/login";
        return "html/login";
    }

    @GetMapping("/login_out")
    public String  loginOut(HttpServletRequest request){
        request.getSession().invalidate();
        return "html/index";
    }

    @ResponseBody
    @PostMapping("/login/verify")
    public ResponseData loginVerify(@RequestBody User user, HttpSession httpSession,
                                    HttpServletRequest request){
        User resultUser=userMapper.existUser(user);
        if(resultUser==null){
            return new ResponseData(false,"用户或者密码错误",null);
        }else {
            if(userInfoMapper.getUserStatusByUsername(user.getUsername())==0){
                return new ResponseData(false,"你的账号已经被禁用了！",null);
            }
            // 校验通过时，在session里放入一个标识
            // 后续通过session里是否存在该标识来判断用户是否登录
            request.getSession().setAttribute("userId",
                    userInfoMapper.getIdByUsername(resultUser.getUsername()));
            return new ResponseData(true,"登陆成功",null);
        }
    }

}
