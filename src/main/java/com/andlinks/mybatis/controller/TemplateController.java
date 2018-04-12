package com.andlinks.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Locale;

/**
 * Created by 陈亚兰 on 2018/2/25.
 */
@Controller
public class TemplateController {

    @ResponseBody
    @RequestMapping(value = "/say",method = RequestMethod.GET)
    public String say(Locale locale, Model model){
        return "hello Wo";
    }

    @RequestMapping("/helloWorld")
    public String index(){
        return "";
    }


    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public ModelAndView helloHtml(ModelMap map){
        ModelAndView mv=new ModelAndView("hello");
        map.addAttribute("name","完事");
        map.addAttribute("host","www.baidu.com");
        return mv;
    }
}
