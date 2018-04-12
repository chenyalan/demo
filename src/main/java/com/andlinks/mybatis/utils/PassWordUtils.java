package com.andlinks.mybatis.utils;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by 陈亚兰 on 2018/2/26
 */
public class PassWordUtils {
    //加密
    public static String privatePassWord(String password,String createTime){
        return DigestUtils.md5Hex(password+createTime);
    }

    //密码匹配
    public static boolean equals(String password,String createTime,String passDB){
        String aa=DigestUtils.md5Hex(password+createTime);
      return   DigestUtils.md5Hex(password+createTime).equals(passDB);
    }
}
