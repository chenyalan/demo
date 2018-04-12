package com.andlinks.mybatis.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 陈亚兰 on 2017/12/21.
 */
public class FileReaderTest {
    public static Logger logger= LoggerFactory.getLogger(FileReaderTest.class);
    public static void main(String[] args) throws IOException {
        FileReader fileReader=null;
        FileWriter fileWriter=null;
        int c=0;
        try {
            fileReader=new FileReader("F:\\cyl.txt");
            fileWriter=new FileWriter("F:\\cyl2.txt");
        } catch (IOException e) {
            logger.error("文件错误");
        }
        while((c=fileReader.read())!=-1){
            System.out.print((char) c);
            fileWriter.write(c);
        }
        fileWriter.flush();
        fileReader.close();
        fileWriter.close();
    }
}
