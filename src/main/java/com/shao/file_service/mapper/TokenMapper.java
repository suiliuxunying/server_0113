package com.shao.file_service.mapper;

import com.shao.file_service.model.Token;

public interface TokenMapper {
    int deleteByPrimaryKey(String token);

    int insert(Token record);

    int insertSelective(Token record);

    Token selectByPrimaryKey(String token);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);
}