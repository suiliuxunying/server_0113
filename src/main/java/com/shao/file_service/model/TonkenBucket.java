package com.shao.file_service.model;

import java.util.Date;

public class TonkenBucket extends TonkenBucketKey {
    private Date authTime;

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }
}