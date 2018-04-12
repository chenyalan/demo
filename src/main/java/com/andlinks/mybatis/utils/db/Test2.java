package com.andlinks.mybatis.utils.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 陈亚兰 on 2018/4/3.
 */
public class Test2 {
    public static void main(String[] args){
        Connection conn = DBConnection.getConnection();
        Statement stmt = null;
        PreparedStatement delete;
        ResultSet rs = null;
        List<User> lists=new ArrayList<>();
        Map<String,Long> map=new HashMap();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id_card,name from chen group by id_card,name HAVING count(1)=2");
            while (rs.next()) {
               User user=new User(rs.getString(1),rs.getString(2));
               lists.add(user);
            }
            rs=stmt.executeQuery("select id,id_card,name from chen");
            String delSql="delete from chen where id=?";
            while(rs.next()){
                Long id=rs.getLong(1);
                String idCard=rs.getString(2);
                String name=rs.getString(3);
                for(User u:lists){
                    if(u.getIdCard()==null||u.getName()==null)continue;
                    try{
                        if(idCard.equals(u.getIdCard())&&name.equals(u.getName())){
                            map.put(idCard,id);
                        }
                    }catch (Exception e){
                        System.out.print("错误:"+u.getIdCard());
                    }

                }
            }
            for(Map.Entry<String,Long> entry:map.entrySet()){
                String key=entry.getKey();
                Long value=entry.getValue();
                delete=conn.prepareStatement(delSql);
                delete.setLong(1,value);
                delete.execute();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } finally {
            DBConnection.closeResultSet(rs);
            DBConnection.closeStatement(stmt);
            DBConnection.closeConnection(conn);
        }
    }
}
