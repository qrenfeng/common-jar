package com.github.qrenfeng.common.entity;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * <p>json-long处理</p>
 * <p>Created by qrf on 2019/4/28.</p>
 * @author qrf
 */
public class LongObjectSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;

        //无值为空
        if (object == null) {
            out.writeNull();
            return;
        }

        //原来的值code
        String strVal = object.toString();
        if (Long.valueOf(strVal) > Integer.MAX_VALUE){
            out.writeString(strVal);
        }else{
            out.writeInt(Integer.valueOf(strVal));
        }

    }
}