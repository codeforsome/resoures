package com.example.health.mapper;

import com.example.health.model.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;


//操作用户信息表
@Component
@Mapper
public interface UserInfoMapper {

    @Update("update user_info set username=#{username},role=#{role},nickname=#{nickname}," +
            "status=#{status} " +
            "where user_info_id=#{userInfoId}")
    void updateUserInfoById(UserInfo userInfo);

    @Select("select user_info_id from user_info where  username=#{username}")
    BigInteger getIdByUsername(String username);

    @Select("select role from user_info where user_info_id=#{id}")
    String getUserRoleById(BigInteger id);

    //    让输入的表内容可以递增
//    @Options(useGeneratedKeys = true,keyProperty = "username")
    @Insert("insert into user_info(username,role,nickname)" +
            "value(#{username},#{role},#{nickname})")
    void insert(UserInfo userInfo);

    @Delete("delete  from user_info where username=#{username}")
    void deleteOne(UserInfo userInfo);


    @Select("select * from user_info where  user_info_id=#{id}")
    UserInfo getUserInfoById(BigInteger id);

    @Select("select status from user_info where  username=#{username}")
    int getUserStatusByUsername(String username);

    @Select("select * from user_info where username=#{username} and user_info_id!=#{userInfoId}")
    UserInfo isExistedUsername(UserInfo userInfo);

    @Select("select * from user_info where username=#{username}")
    UserInfo getUsername(UserInfo userInfo);

    @Select("select * from user_info where nickname=#{nickname} and user_info_id!=#{userInfoId}")
    UserInfo isExistedNickname(UserInfo userInfo);

    @Select("select * from user_info")
    List<UserInfo> selectAll();

    @Select("select * from user_info limit #{offset},#{rows}")
    List<UserInfo> selectPage(@Param("offset")int offset, @Param("rows")int rows);


}
