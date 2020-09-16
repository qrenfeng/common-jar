package com.github.qrenfeng.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * <p>jsong工具类</p>
 * <p>Created by qrf on 2017/8/30.</p>
 * @author qrf
 */
public class JsonUtils {

    /**
     * json转list
     * @param text json字符
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> parseArray(String text, Class<T> clazz) throws Exception {
        try {
            return JSON.parseArray(text,clazz);
        }catch (Exception e){
            throw new Exception("json格式化错误");
        }
    }

    /**
     * json转object
     * @param text json字符
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T parseObject(String text, Class<T> clazz) throws Exception {
        try {
            return JSON.parseObject(text, clazz);
        }catch (Exception e){
            throw new Exception("json格式化错误");
        }
    }

    /**
     * 转json
     * @param object
     * @return
     * @throws Exception
     */
    public static String toJSONString(Object object) throws Exception {
        try {
            return JSON.toJSONString(object,
                    SerializerFeature.WriteNullStringAsEmpty,
                    SerializerFeature.WriteMapNullValue,
                    SerializerFeature.WriteNullNumberAsZero);
        }catch (Exception e){
            throw new Exception("json格式化错误");
        }
    }
}
