package com.hutuxiaogui.autoconfigure.aliyun.oss.config;

import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author cc
 */
public class AliyunOssConfig {
    /**
     * 访问域名
     */
    private String endpoint;
    /**
     * 存储空间名称
     */
    private String bucketName;
    /**
     * 访问密钥，用于标识用户
     */
    private String accessKeyId;
    /**
     * 访问密钥，用户用于加密签名字符串和OSS用来验证签名字符串的密钥
     */
    private String accessKeySecret;
    /**
     * 安全令牌
     */
    private String securityToken;

    @NestedConfigurationProperty
    private AliyunOssClientConfig aliyunOssClientConfig;

    public void init() {

    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return securityToken;
    }

    public void setSecurityToken(String securityToken) {
        this.securityToken = securityToken;
    }

    public AliyunOssClientConfig getAliyunOssClientConfig() {
        return aliyunOssClientConfig;
    }

    public void setAliyunOssClientConfig(AliyunOssClientConfig aliyunOssClientConfig) {
        this.aliyunOssClientConfig = aliyunOssClientConfig;
    }

}
