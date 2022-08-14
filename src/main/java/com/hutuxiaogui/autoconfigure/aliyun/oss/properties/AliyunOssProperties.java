package com.hutuxiaogui.autoconfigure.aliyun.oss.properties;

import com.hutuxiaogui.autoconfigure.aliyun.oss.config.AliyunOssConfig;
import com.hutuxiaogui.autoconfigure.aliyun.oss.constant.OssConstant;
import com.hutuxiaogui.autoconfigure.aliyun.oss.constant.SymbolConstant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cc
 */
@ConfigurationProperties(prefix = OssConstant.ALIYUN + SymbolConstant.DOT + OssConstant.OSS)
public class AliyunOssProperties extends AliyunOssConfig implements InitializingBean {

    /**
     * 是否开启 oss 自动装配，默认为 true
     */
    private Boolean enable = true;

    /**
     * 多实例
     */
    private Map<String, AliyunOssConfig> names = new HashMap<>();

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Map<String, AliyunOssConfig> getNames() {
        return names;
    }

    public void setNames(Map<String, AliyunOssConfig> names) {
        this.names = names;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (names.isEmpty()) {
            this.init();
        } else {
            names.values().forEach(AliyunOssConfig::init);
        }
    }
}
