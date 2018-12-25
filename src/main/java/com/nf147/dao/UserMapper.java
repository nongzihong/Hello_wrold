package com.nf147.dao;

import com.nf147.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //使用 xml配置文件
    int check(User user);

    //使用注解
    @Select ("select * from user where  userName = #{userName} and passwrod =#{password}")
    int check1(User user);

}
