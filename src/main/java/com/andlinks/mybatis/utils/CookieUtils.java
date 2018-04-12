package com.andlinks.mybatis.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 陈亚兰 on 2018/3/6.
 */
public class CookieUtils {
    public static Cookie addCookie(HttpServletResponse response,String key, String value, int time){
        Cookie cookie =new Cookie(key,value);
        cookie.setMaxAge(time);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }
}
