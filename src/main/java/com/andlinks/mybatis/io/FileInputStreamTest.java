package com.andlinks.mybatis.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by 陈亚兰 on 2017/12/21.
 */
public class FileInputStreamTest {
    public static Logger logger= LoggerFactory.getLogger(FileInputStreamTest.class);
    public static void main(String[] args) throws IOException {
        InputStream in=null;
        OutputStream out=null;
        byte[] bytes=new byte[1024];
        try {
            out=new FileOutputStream("F:\\cy432.txt");
        } catch (FileNotFoundException e) {
            logger.error("写文件错误");
        }
        int num=0;
        int byteStart=0;
        try {
             in = new FileInputStream("F:\\zoe\\springboot-mybatis-xml\\src\\main\\java\\com\\andlinks\\mybatis\\controller\\Handler.java");
        } catch (FileNotFoundException e) {
            logger.error("文件错误");
        }
        try {
            while((num=in.read(bytes))!=-1){
                out.write(bytes,0,num);//从0开始放得到的bytes off是在bytes数组中的位置
                out.flush();
                System.out.print(new String(bytes,0,num));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            in.close();
            out.close();
        }
    }
}
