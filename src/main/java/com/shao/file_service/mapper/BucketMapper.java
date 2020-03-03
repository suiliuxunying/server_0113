package com.shao.file_service.mapper;

import com.shao.file_service.model.Bucket;

public interface BucketMapper {
    int deleteByPrimaryKey(String bucketId);

    int insert(Bucket record);

    int insertSelective(Bucket record);

    Bucket selectByPrimaryKey(String bucketId);

    int updateByPrimaryKeySelective(Bucket record);

    int updateByPrimaryKey(Bucket record);
}