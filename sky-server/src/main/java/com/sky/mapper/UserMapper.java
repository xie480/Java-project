package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {
    @Select("select * from user where openid = #{openid}")
    User getByOpenid(String openid);

    void saveUser(User user);

    @Select("select * from user where id = #{userId}")
    User findById(Long userId);

    Integer findUserCount(LocalDateTime beginTime, LocalDateTime endTime);
}
