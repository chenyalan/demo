package com.andlinks.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by 陈亚兰 on 2017/12/19.
 */
public class SocketT1 {
    private static final String host="time-A.timefreq.bldrdoc.gov";
    private static final Integer port=13;
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket(host,port);
        InputStream stream=socket.getInputStream();
        Scanner in=new Scanner(stream);
        while (in.hasNextLine()){
            String line=in.nextLine();
            System.out.println(line);
        }
    }
}
