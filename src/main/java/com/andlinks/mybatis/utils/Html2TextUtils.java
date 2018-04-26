package com.andlinks.mybatis.utils;

import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.*;

/**
 * Created by 陈亚兰 on 2018/4/20.
 */
public class Html2TextUtils extends HTMLEditorKit.ParserCallback {
    private static Html2TextUtils html2TextUtils=new Html2TextUtils();

    StringBuffer sb;

    public Html2TextUtils(){}

    public void parse(String str) throws IOException {
        InputStream iin=new ByteArrayInputStream(str.getBytes());
        Reader in=new InputStreamReader(iin);
        sb=new StringBuffer();
        ParserDelegator delegator=new ParserDelegator();
        delegator.parse(in,this,Boolean.TRUE);
        iin.close();
        in.close();
    }

    public void handleText(char[] text,int pos){
        sb.append(text);
    }

    public String getText(){
        return sb.toString();
    }

    public static String getContent(String str) throws IOException {
        html2TextUtils.parse(str);
        return html2TextUtils.getText();
    }

    public static void main(String[] args) throws IOException {
        String text=Html2TextUtils.getContent("<p>\n" +
                "    反对法<img src=\"http://ueditor.baidu.com/server/umeditor/upload/demo.jpg\"/>\n" +
                "</p>");
        System.out.print(text);
    }
}
