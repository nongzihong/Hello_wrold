package com.nf147.aspect;

import com.nf147.dao.UserMapper;
import com.nf147.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component //注册容器
@Aspect //声明这是一个切面类
public class UserPowerAdvice {

    @Autowired
    HttpSession session;

    @Autowired
    UserMapper userMapper;


    //切点
    @Pointcut("execution(* com.nf147.controller.*.login(..))")
    public void power(){

    }

    //换人通知
    @Around("power()")
    public Object seeStatus(ProceedingJoinPoint joinPoint) {

        try {

            //。。。。。前环绕通知----> 在原方法还没有执行前执行

            Object proceed = joinPoint.proceed();//

            //。。。。。。后环绕通知执行后执行

            //从session中取出 存储的信息
            User user = (User) session.getAttribute("username");

            if (!user.getUserName().isEmpty() && !user.getPassword().isEmpty()) {
                //去数据库中查询,结果大于 0 则存在用户 否则
                int result = userMapper.check(user);
                if (result > 0) {
                    if (user.getUserName().equals("admin")) {
                        return "{\"msg\":\"该用户 " + user.getUserName() + " 是 管理员\"}";
                    } else {
                        return "{\"msg\":\"该用户 " + user.getUserName() + " 不是 管理员\"}";
                    }
                } else {
                    return "{\"msg\":\"用户不存在。。\"}";
                }
            } else {
                return "{\"msg\":\"用户名或者密码为空。。\"}";
            }

        }catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
