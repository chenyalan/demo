package com.andlinks.mybatis.interception;

import com.andlinks.mybatis.entity.redis.UserRedis;
import com.andlinks.mybatis.service.redis.UserRedisService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by 陈亚兰 on 2018/3/5.
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRedisService userRedisService;
    @Value("${redis.time}")
    private int time;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Cookie userCookie= WebUtils.getCookie(request,"user");
        UserRedis userRedis;
        if(userCookie!=null){
            userRedis=userRedisService.findByAccount(userCookie.getValue());
            if(userRedis==null){
                request
                        .getRequestDispatcher(String.format("%s=%s","/loginFail?info", "缓存不存在"))
                        .forward(request, response);
                return false;
            }
        }else {
            request.getRequestDispatcher(String.format("%s=%s","/loginFail?info","cookie不存在")).forward(request,response);
            return false;
        }
        if(!userRedis.getAccount().equals(userCookie.getValue())){
            request.getRequestDispatcher(String.format("%s=%s","/loginFail?info","cookie和redis所存用户名不一致")).forward(request,response);
            return false;
        }
        if(new Date().after(userRedis.getDealTime())){
            request.getRequestDispatcher(String.format("%s=%s","/loginFailure?info","缓存过期")).forward(request,response);
            return false;
        }else{
            userRedis.setDealTime(DateUtils.addSeconds(new Date(),time));
            userRedisService.save(userRedis);
        }
        System.out.println("now:"+new Date());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
