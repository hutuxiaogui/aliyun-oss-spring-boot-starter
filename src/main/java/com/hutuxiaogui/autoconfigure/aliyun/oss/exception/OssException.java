package com.hutuxiaogui.autoconfigure.aliyun.oss.exception;

import org.springframework.lang.Nullable;

/**
 * @author cc
 */
public class OssException extends RuntimeException {

    public OssException(String msg) {
        super(msg);
    }

    public OssException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
