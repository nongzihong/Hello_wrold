package com.nf147.servlet;

import com.nf147.dao.UserMapper;
import com.nf147.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServlet {

    @Autowired
    private UserMapper userMapper;


    public Boolean checkUser(User user) {
        //根据传进来的参数 去数据库查询用户
        int result = userMapper.check(user);

        //如果查询结果不为零 说明数据库存在该用户，让它登录，否则不让它登录
        if (result > 0) {
            return true;
        }
        return false;
    }


}
