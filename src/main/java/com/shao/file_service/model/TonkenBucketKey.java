package com.shao.file_service.model;

public class TonkenBucketKey {
    private String bucketName;

    private String targetToken;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName == null ? null : bucketName.trim();
    }

    public String getTargetToken() {
        return targetToken;
    }

    public void setTargetToken(String targetToken) {
        this.targetToken = targetToken == null ? null : targetToken.trim();
    }
}