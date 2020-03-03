package com.shao.file_service.service;

import com.shao.file_service.mapper.UserMapper;
import com.shao.file_service.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserMapper userMapper;
    @Override
    public int deleteByPrimaryKey(final String userId) {
        
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(final User record) {
        
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(final User record) {

        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(final String userId) {
        
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(final User record) {
        
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(final User record) {
        
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public User getUserInfoDyId(final String userId) {
        // TODO Auto-generated method stub
        return null;
    }


}