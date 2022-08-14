package com.hutuxiaogui.autoconfigure.aliyun.oss.util;

/**
 * @author cc
 */
public class ArrayUtils {

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return null != array && array.length != 0;
    }
}
