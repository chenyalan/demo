package com.andlinks.mybatis.controller;

import com.andlinks.mybatis.common.ResultData;
import com.andlinks.mybatis.entity.httpdo.HttpDO;
import com.andlinks.mybatis.utils.HttpUtils;
import com.google.gson.reflect.TypeToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

/**
 * Created by 陈亚兰 on 2018/5/23.
 */
@RestController
@RequestMapping(value = "/test")
public class DemoController {
    @RequestMapping(value = "/gsonType",method = RequestMethod.GET)
    public ResultData<HttpDO> getHttpDO(){
        String getUrl="http://42.123.99.75:20001/api/changcheng/company/news?page={0}&count={1}";
        try{
            HttpDO resultData= HttpUtils.get(MessageFormat.format(getUrl, new Object[]{1,10}),new TypeToken<HttpDO>(){});
            return ResultData.success(resultData);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResultData.error(null);
    }
}
