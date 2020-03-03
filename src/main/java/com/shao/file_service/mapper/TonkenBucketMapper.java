package com.shao.file_service.mapper;

import com.shao.file_service.model.TonkenBucket;
import com.shao.file_service.model.TonkenBucketKey;

public interface TonkenBucketMapper {
    int deleteByPrimaryKey(TonkenBucketKey key);

    int insert(TonkenBucket record);

    int insertSelective(TonkenBucket record);

    TonkenBucket selectByPrimaryKey(TonkenBucketKey key);

    int updateByPrimaryKeySelective(TonkenBucket record);

    int updateByPrimaryKey(TonkenBucket record);
}