package com.andlinks.mybatis.utils;

import java.io.IOException;

/**
 * Created by 陈亚兰 on 2018/3/31.
 */
public class PoiTest {
    public static void main(String[]args) throws IOException {
        String path="C:\\Users\\Administrator\\Desktop\\y3";
        String toPath="C:\\Users\\Administrator\\Desktop\\to\\";
        ExcelUtils.putAllFilesInOneDirectory(path,toPath);
        ExcelUtils.getFiles(path);
    }
}
