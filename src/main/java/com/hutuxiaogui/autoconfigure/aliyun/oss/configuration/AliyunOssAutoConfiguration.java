package com.hutuxiaogui.autoconfigure.aliyun.oss.configuration;

import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.aliyun.oss.OSSClientBuilder;
import com.hutuxiaogui.autoconfigure.aliyun.oss.client.AliyunOssClient;
import com.hutuxiaogui.autoconfigure.aliyun.oss.config.AliyunOssClientConfig;
import com.hutuxiaogui.autoconfigure.aliyun.oss.config.AliyunOssConfig;
import com.hutuxiaogui.autoconfigure.aliyun.oss.properties.AliyunOssProperties;
import com.hutuxiaogui.autoconfigure.aliyun.oss.util.SpringUtils;

/**
 * @author cc
 */
@SpringBootConfiguration
@ConditionalOnClass(AliyunOssClient.class)
@EnableConfigurationProperties(AliyunOssProperties.class)
public class AliyunOssAutoConfiguration {

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    /**
     * 生成实例
     * @return AliyunOssClient
     */
    @Bean
    public AliyunOssClient aliyunOssClient() {
        if(aliyunOssProperties.getEnable().equals(false)){
            return null;
        }
        Map<String, AliyunOssConfig> aliyunOssPropertiesMap = aliyunOssProperties.getNames();
        if (aliyunOssPropertiesMap.isEmpty()) {
            return aliyunOssClient(aliyunOssProperties);
        } else {
            AliyunOssProperties aliyunOssProperties = new AliyunOssProperties();
            String endpoint = this.aliyunOssProperties.getEndpoint();
            String bucketName = this.aliyunOssProperties.getBucketName();
            String accessKeyId = this.aliyunOssProperties.getAccessKeyId();
            String accessKeySecret = this.aliyunOssProperties.getAccessKeySecret();
            String securityToken = this.aliyunOssProperties.getSecurityToken();
            aliyunOssPropertiesMap.forEach((name, aliyunOssConfig) -> {
                if (ObjectUtils.isEmpty(aliyunOssConfig.getEndpoint())) {
                    aliyunOssProperties.setEndpoint(endpoint);
                } else {
                    aliyunOssProperties.setEndpoint(aliyunOssConfig.getEndpoint());
                }
                if (ObjectUtils.isEmpty(aliyunOssConfig.getBucketName())) {
                    aliyunOssProperties.setBucketName(bucketName);
                } else {
                    aliyunOssProperties.setBucketName(aliyunOssConfig.getBucketName());
                }
                if (ObjectUtils.isEmpty(aliyunOssConfig.getAccessKeyId())) {
                    aliyunOssProperties.setAccessKeyId(accessKeyId);
                } else {
                    aliyunOssProperties.setAccessKeyId(aliyunOssConfig.getAccessKeyId());
                }
                if (ObjectUtils.isEmpty(aliyunOssConfig.getAccessKeySecret())) {
                    aliyunOssProperties.setAccessKeySecret(accessKeySecret);
                } else {
                    aliyunOssProperties.setAccessKeySecret(aliyunOssConfig.getAccessKeySecret());
                }
                if (ObjectUtils.isEmpty(aliyunOssConfig.getSecurityToken())) {
                    aliyunOssConfig.setSecurityToken(securityToken);
                } else {
                    aliyunOssProperties.setAccessKeySecret(aliyunOssConfig.getSecurityToken());
                }
                aliyunOssProperties.setAliyunOssClientConfig(aliyunOssConfig.getAliyunOssClientConfig());
                // 各个实例单独使用各自的配置
                registerBean(name, aliyunOssClient(aliyunOssProperties));
            });
        }
        return null;
    }

    /**
     * 根据配置生成对应的  AliyunOssClient 对象
     * @param aliyunOssProperties
     * @return
     */
    private AliyunOssClient aliyunOssClient(AliyunOssProperties aliyunOssProperties) {
        AliyunOssClient aliyunOssClient = new AliyunOssClient();
        aliyunOssClient.setAliyunOssProperties(aliyunOssProperties);
        if (ObjectUtils.isEmpty(aliyunOssProperties.getSecurityToken())
            && ObjectUtils.isNotEmpty(aliyunOssProperties.getAliyunOssClientConfig())) {
            aliyunOssClient.setOss(new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(),
                aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(),
                aliyunOssProperties.getAliyunOssClientConfig().toClientConfig()));
            return aliyunOssClient;
        }
        if (ObjectUtils.isNotEmpty(aliyunOssProperties.getSecurityToken())
            && ObjectUtils.isEmpty(aliyunOssProperties.getAliyunOssClientConfig())) {
            aliyunOssClient.setOss(new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(),
                aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret()));
            return aliyunOssClient;
        }
        aliyunOssClient.setOss(new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(),
            aliyunOssProperties.getAccessKeyId(), aliyunOssProperties.getAccessKeySecret(),
            aliyunOssProperties.getSecurityToken(), Optional.ofNullable(aliyunOssProperties.getAliyunOssClientConfig())
                .orElse(new AliyunOssClientConfig()).toClientConfig()));
        return aliyunOssClient;
    }

    /**
     * 注册 bean
     * @param beanName 名称
     * @param bean bean 类型
     * @param <T> bean 类型
     */
    public static <T> void registerBean(String beanName, T bean) {
        SpringUtils.registerBean(beanName, bean);
    }
}
