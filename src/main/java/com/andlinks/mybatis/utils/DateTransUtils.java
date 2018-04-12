package com.andlinks.mybatis.utils;

import com.andlinks.mybatis.service.SysService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 陈亚兰 on 2017/8/8.
 */
public class DateTransUtils {
    //日期格式
   public static Date getDate(String date) throws ParseException {
       SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return dateFormat.parse(date);
   }
   //字符串
   public static String getStringDate(Date date){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return  sdf.format(date);
   }

   //精确到毫秒
   public static String getStringSec(Date date){
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
       return sdf.format(date);
   }
}
