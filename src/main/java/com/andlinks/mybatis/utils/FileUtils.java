package com.andlinks.mybatis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by 陈亚兰 on 2017/12/21.
 */
public class FileUtils {
    public static Logger logger= LoggerFactory.getLogger(FileUtils.class);

    /**
     * 从文件中读取到内存中
     * @param file
     * @throws IOException
     */
    public static void read(File file) throws IOException {
           InputStream in=new FileInputStream(file);
           byte[] bytes=new byte[1024];
           int num=0;
           while((num=in.read(bytes))!=-1){
               System.out.print(new String(bytes,0,num));
           }
           in.close();
    }

    /**
     * 从内存(程序)写到文件中，File如果不存在，自动建立。
     * @param file
     * @param a
     * @throws IOException
     */
    public static void write(File file,String a) throws IOException {
           OutputStream out=new FileOutputStream(file,true);
           byte[] bytes=a.getBytes("UTF8");
        //   byte[] bytes1=a.getBytes("ISO-8859-1");
           out.write(bytes);
           out.flush();
           out.close();
    }
    public static void main(String[] args) throws IOException {
        File file=new File("f:\\c1.txt");
        String a="陈亚兰chenyalan";
        write(file,a);
        read(file);
    }
}
