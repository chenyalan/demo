package com.andlinks.mybatis.utils;

import java.io.*;
import java.util.Arrays;

/**
 * created by 陈亚兰 on 18-4-26
 */
public class File2ManyFiles {
    public static void main(String[] args) {
        //先将源文件读取到内存中
        int eachSize=5000*1024;
        File srcFile =new File("/home/cyl/桌面/p.docx");
        //创建一个文件对象
        splitFile(srcFile,eachSize);
    }
    public static void splitFile(File srcFile,int eachSize){
        //判断文件是否符合拆分要求
        if(srcFile.length()==0){
            throw new RuntimeException("文件不符合拆分要求");
        }
        byte[] fileContent= new byte[(int) srcFile.length()];
        try {
            //将文件内容读取到内存中
            FileInputStream fis=new FileInputStream(srcFile);
            fis.read(fileContent);
            fis.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //计算要次要拆分为多少份
        int fileNumber;
        if(fileContent.length%eachSize==0){
            fileNumber = fileContent.length/eachSize;
        } else{
            fileNumber = fileContent.length/eachSize+1;
        }
        for (int i=0;i<fileNumber;i++){
            String fileName = srcFile.getName().substring(0,srcFile.getName().indexOf("."))+"-"+i+".docx";
            File fi = new File("/home/cyl/桌面/to/", fileName);
            //在当前文件路径下创建拆分的文件
            byte[] eachContent;
            //将源文件内容复制到拆分的文件中
            if(i!=fileNumber-1){
                eachContent = Arrays.copyOfRange(fileContent, eachSize*i, eachSize*(i+1));
            } else{
                eachContent = Arrays.copyOfRange(fileContent, eachSize*i, fileContent.length);
            }
            try {
                FileOutputStream fos = new FileOutputStream(fi);
                fos.write(eachContent);
                fos.close();
                System.out.printf("输出子文件 %s,其大小是 %d,每个的大小是%d\n",fi.getAbsoluteFile(),fi.length(),eachContent.length);
            }
            catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
