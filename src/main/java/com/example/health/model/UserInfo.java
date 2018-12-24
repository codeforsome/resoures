package com.example.health.model;

import java.math.BigInteger;
import java.util.Date;

public class UserInfo {
    private String username;
    private Boolean status;
    private Date registerDate;
    private String role;
    private BigInteger userInfoId;
    private  String  nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigInteger getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(BigInteger userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }


}
