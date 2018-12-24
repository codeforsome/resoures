package com.example.health.utils;

import com.example.health.model.ResponseData;
import com.example.health.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


//专门用来检查一些用户输入是否符合要求
@Component
public class CheckInputValidityUtil {
    @Value("${user.nickname.max-length}")
    private int nicknameMinLength;

    @Value("${user.username.min-length}")
    private int usernameMinLength;

    @Value("${user.username.max-length}")
    private int usernameMaxLength;

    @Value("${user.password.min-length}")
    private int passwordMinLength;

    @Value("${user.password.max-length}")
    private int passwordMaxLength;

    public  ResponseData checkUserValidity(User user){
        ResponseData result;
        result=checkUsernameValidity(user.getUsername());
        if(result==null){//继续检查
            result=checkPasswordValidity(user.getPassword());
            if (result==null){
                return null;
            }else {
                return result;
            }
        }else {
            return result;
        }
    }
    public  ResponseData checkUsernameValidity(String username){
        String reg="^[A-Za-z0-9]+$";
        if(username==null){
            return new ResponseData(false,"账号名不能为null",null);
        }else if(username.length()<usernameMinLength){
            return new ResponseData(false,"账号名长度不能低于"+
                    usernameMinLength,null);
        }else if(username.length()>usernameMaxLength){
            return new ResponseData(false,"账号名长度不能高于"+
                    usernameMaxLength,null);
        }else if(!username.matches(reg)){
            return new ResponseData(false,"账号名不能包含字母数字以外的字符",null);
        }
        return null;
    }
    public  ResponseData checkNicknameValidity(String username){
        if(username==null){
            return new ResponseData(false,"昵称名不能为null",null);
        }else if(username.length()>usernameMaxLength){
            return new ResponseData(false,"昵称名长度不能高于"+
                    nicknameMinLength,null);
        }
        return null;
    }

    public  ResponseData checkPasswordValidity(String password){
        String reg="^[A-Za-z0-9]+$";
        if(password==null){
            return new ResponseData(false,"密码不能为null",null);
        }else if(password.length()<usernameMinLength){
            return new ResponseData(false,"密码长度不能低于"+
                    passwordMinLength,null);
        }else if(password.length()>usernameMaxLength){
            return new ResponseData(false,"密码长度不能高于"+
                    passwordMaxLength,null);
        }else if(!password.matches(reg)){
            return new ResponseData(false,"密码不能包含字母数字以外的字符",null);
        }
        return null;
    }
}
