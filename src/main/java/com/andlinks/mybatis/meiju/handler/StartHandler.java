//package com.andlinks.mybatis.meiju.handler;
//
//import com.andlinks.mybatis.meiju.State;
//import org.apache.ibatis.type.JdbcType;
//import org.apache.ibatis.type.TypeHandler;
//
//import java.sql.CallableStatement;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
///**
// * Created by 陈亚兰 on 2017/11/30.
// */
//public class StartHandler implements TypeHandler<State> {
//    @Override
//    public void setParameter(PreparedStatement ps, int i, State state, JdbcType jdbcType) throws SQLException {
//        ps.setInt(i,state.getNum());
//    }
//
//    @Override
//    public State getResult(ResultSet rs, String s) throws SQLException {
//        return State.getState(rs.getInt(s));
//    }
//
//    @Override
//    public State getResult(ResultSet rs, int i) throws SQLException {
//        return State.getState(rs.getInt(i));
//    }
//
//    @Override
//    public State getResult(CallableStatement cs, int i) throws SQLException {
//        return State.getState(cs.getInt(i));
//    }
//}
