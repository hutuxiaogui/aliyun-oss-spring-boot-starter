package com.hutuxiaogui.autoconfigure.aliyun.oss;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.hutuxiaogui.autoconfigure.aliyun.oss.properties.AliyunOssProperties;

/**
 * @author cc
 */
@Configuration
@ComponentScan(basePackages = "com.hutuxiaogui.autoconfigure.aliyun.oss")
@EnableConfigurationProperties({AliyunOssProperties.class})
public class EnableAliyunOssAutoConfiguration {
}
