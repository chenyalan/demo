package com.andlinks.mybatis.utils.db;

import java.io.File;
import java.sql.*;

/**
 * Created by 陈亚兰 on 2018/4/3.
 */
public class Test {
    public static void main(String[] args){
        Connection conn = DBConnection.getConnection();
        Statement stmt = null;
        PreparedStatement update;
        ResultSet rs = null;
        String path="F:\\photos\\";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select photo,id from officer_basic_info;");
            String updateSql="update officer_basic_info set photo=null where id=?";
            while (rs.next()) {
                String photo=rs.getString(1);
                Long id=rs.getLong(2);
                if(photo==null)continue;
                String dealWith=photo.substring(photo.lastIndexOf("/")+1,photo.length());
                File isExist=new File(path+dealWith);
                if(isExist.exists()){
                    System.out.println("id:"+ id+"  "+dealWith+"  存在");
                }else{
                    System.out.println("id:"+ id+"  "+dealWith+"  不存在");
                    update=conn.prepareStatement(updateSql);
                    update.setLong(1,id);
                    update.execute();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(conn);
        }
    }
}
