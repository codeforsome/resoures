package com.example.health.mapper;

import com.example.health.model.User;
import com.example.health.model.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    @Select("select * from user_login")
     List<User> selectAll();

    //    让输入的表内容可以递增
//    @Options(useGeneratedKeys = true,keyProperty = "username")
    @Insert("insert into user_login(username,password)" +
            "value(#{username},#{password})")
    void insert(User user);


    @Select("select * from user_login where username=#{username} and password=#{password}")
    User existUser(User user);

    @Select("select * from user_login where username=#{username}")
    User existUsername(User user);

    @Delete("delete  from user_login where username=#{username}")
     void deleteOne(User user);
}
