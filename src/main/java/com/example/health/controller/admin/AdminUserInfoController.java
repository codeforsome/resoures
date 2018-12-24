package com.example.health.controller.admin;

import com.example.health.mapper.UserInfoMapper;
import com.example.health.mapper.UserMapper;
import com.example.health.model.ResponseData;
import com.example.health.model.User;
import com.example.health.model.UserInfo;
import com.example.health.utils.CheckInputValidityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//处理后台用户信息管理请求
@Controller
@RequestMapping("admin/userinfo")
public class AdminUserInfoController {
    @Autowired
    CheckInputValidityUtil checkInputValidityUtil;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/")
    public String  get(){
        return "adminhtml/userinfo";
    }

    //处理更新用户表
    @ResponseBody
    @PostMapping("/update")
    public ResponseData update(@RequestBody UserInfo userInfo){
        if(userInfo==null){
           return new ResponseData(false,"提交信息有错误",null);
        }
        if(checkInputValidityUtil.checkNicknameValidity(userInfo.getNickname())!=null){
            return checkInputValidityUtil.checkNicknameValidity(userInfo.getNickname());
        }
        if(checkInputValidityUtil.checkUsernameValidity(userInfo.getUsername())==null){
            if(userInfoMapper.getUserInfoById(userInfo.getUserInfoId())==null){
                return new ResponseData(false,"提交的账号不存在",null);
            } else if(userInfoMapper.isExistedUsername(userInfo)!=null){
                return  new ResponseData(false,"修改的账号已经存在",null);
            }else if(userInfoMapper.isExistedNickname(userInfo)!=null){
                return  new ResponseData(false,"修改的昵称已经存在",null);
            } else {
                userInfoMapper.updateUserInfoById(userInfo);
                return new ResponseData(true, "修改成功", null);
            }
        }else {
            return checkInputValidityUtil.checkUsernameValidity(userInfo.getUsername());
        }

    }

    @ResponseBody
    @PostMapping("/insert")
    public ResponseData  insert(@RequestBody UserInfo userInfo){
        if(checkInputValidityUtil.checkUsernameValidity(userInfo.getUsername())!=null){
            return checkInputValidityUtil.checkUsernameValidity(userInfo.getUsername());
        }
        if(userInfoMapper.getUsername(userInfo)!=null){
            return  new ResponseData(false,"添加的账号已经存在",null);
        }else{
            userInfo.setNickname(userInfo.getUsername());
            userInfoMapper.insert(userInfo);
            User user=new User();
            user.setUsername(userInfo.getUsername());
            user.setPassword(userInfo.getUsername());
            userMapper.insert(user);
            return  new ResponseData(true,"添加成功",null);
        }
    }



    @ResponseBody
    @GetMapping("/getinfo")
    public List<UserInfo>  getUserInfo(int currentPage,int pageSize){
        return userInfoMapper.selectPage(currentPage-1,pageSize);
    }

    @ResponseBody
    @PostMapping("/delete-user")
    public String   deleteUser(@RequestBody UserInfo userInfo){
        userInfoMapper.deleteOne(userInfo);
        User user=new User();
        user.setUsername(userInfo.getUsername());
        userMapper.deleteOne(user);
        return "ok";
    }

}
