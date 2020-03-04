package com.shao.file_service.service;

import com.shao.file_service.model.User;

/**
 * UserServiceInterface
 */
public interface UserServiceInterface {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    // public User getUserInfoDyId(String userId);
    
}