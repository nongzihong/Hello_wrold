package com.nf147.controller;

import com.nf147.entity.User;
import com.nf147.servlet.UserServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Objects;

@RestController
public class LoginController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserServlet userServlet;

    @RequestMapping(path = "/login/{name}/{password}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String login(@PathVariable("name") String name, @PathVariable("password") String password, HttpSession session) {
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        session.setAttribute("username",user); //存入session中
        Boolean b = userServlet.checkUser(user);

        if (b){
            return "{\"msg\":\"登录成功\"}";
        }else {
            return "{\"msg\":\"登录失败\"}";
        }
    }



    @GetMapping(path = "/IOC")
    public HashMap<String,String[]> getAllinfo(){
        return new HashMap<String,String[]>(){{
           put("子容器",webApplicationContext.getBeanDefinitionNames());
           put("父容器", Objects.requireNonNull(webApplicationContext).getBeanDefinitionNames());
        }};
    }


}

