package com.example.health.controller;

import com.example.health.mapper.UserInfoMapper;
import com.example.health.mapper.UserMapper;
import com.example.health.model.ResponseData;
import com.example.health.model.User;
import com.example.health.model.UserInfo;
import com.example.health.utils.CheckInputValidityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    CheckInputValidityUtil checkInputValidityUtil;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    @GetMapping("")
    public String getRegister(){
        return "html/register";
    }

    @ResponseBody
    @PostMapping("/register-user")
    public ResponseData registerUser(@RequestBody User user, HttpServletRequest request){
        if(checkInputValidityUtil.checkUserValidity(user)==null){
            if(userMapper.existUsername(user)!=null){
                return new ResponseData(false,"该用户已经注册",null);
            }else{
                UserInfo userInfo=new UserInfo();
                userInfo.setRole("普通用户");
                userInfo.setNickname(user.getUsername());
                userInfo.setUsername(user.getUsername());
                userInfoMapper.insert(userInfo);
                userMapper.insert(user);
                request.getSession().setAttribute("userId",
                        userInfoMapper.getIdByUsername(user.getUsername()));
                return new ResponseData(true,"注册成功",null);
            }
        }else {
            return checkInputValidityUtil.checkUserValidity(user);
        }

    }

}
