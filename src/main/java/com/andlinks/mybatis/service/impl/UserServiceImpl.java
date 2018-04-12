//package com.andlinks.mybatis.service.impl;
//
//import com.andlinks.mybatis.dao.UserMapper;
//import com.andlinks.mybatis.entity.User;
//import com.andlinks.mybatis.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * Created by 陈亚兰 on 2017/11/28.
// */
//@Service
//public class UserServiceImpl implements UserService {
//    @Autowired
//    private UserMapper userMapper;
//
//    @Override
//    public int deleteByPrimaryKey(Integer id) {
//        return userMapper.deleteByPrimaryKey(id);
//    }
//
//    @Override
//    public int insert(User record) {
//        return userMapper.insert(record);
//    }
//
//    @Override
//    public int insertSelective(User record) {
//        return userMapper.insertSelective(record);
//    }
//
//    @Override
//    public User selectByPrimaryKey(Integer id) {
//        return userMapper.selectByPrimaryKey(id);
//    }
//
//    @Override
//    public int updateByPrimaryKeySelective(User record) {
//        return userMapper.updateByPrimaryKeySelective(record);
//    }
//
//    @Override
//    public int updateByPrimaryKey(User record) {
//        return userMapper.updateByPrimaryKey(record);
//    }
//
//    @Override
//    public User findByNickName(String nickName) {
//        return userMapper.findByNickName(nickName);
//    }
//
//    @Override
//    public List<User> findAll() {
//        return userMapper.findAll();
//    }
//}
