package com.andlinks.mybatis.utils;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;

/**
 * Created by 陈亚兰 on 2018/4/20.
 */
public class RTFUtils {
    public static String getText(String filePath) throws IOException, BadLocationException {
        String result=null;
        File file=new File(filePath);
        DefaultStyledDocument styledDocument=new DefaultStyledDocument();
        //创建文件输入流
        InputStream stream=new FileInputStream(file);
        new RTFEditorKit().read(stream,styledDocument,0);
        //以IS0-8859-1的编码形式获取字节byte[],并以gbk的编码形式生成字符串
        result = new String(styledDocument.getText(0, styledDocument.getLength()).getBytes("ISO8859-1"),"UTF-8");
        return result;
    }
    public static void main(String[] args) throws IOException, BadLocationException {
        String result=getText("C:\\Users\\Administrator\\Desktop\\fjk.rtf");
        System.out.print(result);
    }
}
