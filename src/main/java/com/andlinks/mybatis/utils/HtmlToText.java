package com.andlinks.mybatis.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 陈亚兰 on 2018/4/20.
 */
public class HtmlToText {
    private static final Pattern p_script = Pattern.compile("<[a-zA-z]{1,9}((?!>).)*>", Pattern.CASE_INSENSITIVE);

    private static final Pattern t_script = Pattern.compile("</[a-zA-z]{1,9}>", Pattern.CASE_INSENSITIVE);



    public static String getTextByHtml(String html){

        Matcher m_script = p_script.matcher(html);

        html = m_script.replaceAll("");

        Matcher l_script = t_script.matcher(html);

        return l_script.replaceAll("");

    }



    public static void main(String[] args) {

        String str = "<table><tr><td>自测通过</td></tr></table><br><p>sds</p><img id='img1' src='http://www.baidu.com/img/baidu_logo.gif' width='100' height='50' alt=''> HTML字符串转文本<br><img src='http://www.baidu.com/img/baidu_logo.gif' width='100' height='50' alt=''>";
        System.out.println(getTextByHtml(str));

    }
}
