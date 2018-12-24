package com.example.health.mapper;

import com.example.health.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Mapper
public interface AdminMapper {
    @Select("select * from admin_login where username=#{username} and " +
            "password=#{password}")
    User selectIsEntry(User user);
}
