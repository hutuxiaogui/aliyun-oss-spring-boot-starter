package com.hutuxiaogui.autoconfigure.aliyun.oss.client;

import com.aliyun.oss.OSS;
import com.hutuxiaogui.autoconfigure.aliyun.oss.properties.AliyunOssProperties;

/**
 * @author cc
 */
public class AliyunOssClient {

    private OSS oss;

    private AliyunOssProperties aliyunOssProperties;

    public AliyunOssClient(){

    }

    public AliyunOssClient(OSS oss, AliyunOssProperties aliyunOssProperties) {
        this.oss = oss;
        this.aliyunOssProperties = aliyunOssProperties;
    }

    public String getBucketName() {
        String bucketName = aliyunOssProperties.getBucketName();
        if (!oss.doesBucketExist(bucketName)) {
            oss.createBucket(bucketName);
        }
        return bucketName;
    }

    public OSS oss() {
        return oss;
    }

    public void setOss(OSS oss) {
        this.oss = oss;
    }

    public AliyunOssProperties getAliyunOssProperties() {
        return aliyunOssProperties;
    }

    public void setAliyunOssProperties(AliyunOssProperties aliyunOssProperties) {
        this.aliyunOssProperties = aliyunOssProperties;
    }
}
