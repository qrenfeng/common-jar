package com.github.qrenfeng.common.entity;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ListSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>json-long处理</p>
 * <p>Created by qrf on 2019/4/28.</p>
 * @author qrf
 */
public class ListLongSerializer implements ObjectSerializer {

    private final String LIST_LONG_FORMAT = "java.util.List<java.lang.Long>";

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter out = serializer.out;
        //无值为空
        if (object == null) {
            out.writeNull();
            return;
        }

        if (fieldType!=null && LIST_LONG_FORMAT.equals(fieldType.getTypeName())) {
            //原来的值code
            List<Long> strVals = (List<Long>) object;
            List<String> results = new ArrayList<>();
            for (Long str : strVals) {
                if (str != null) {
                    results.add(str.toString());
                }
            }
            out.write(results);
            return;
        }

        ListSerializer.instance.write(serializer, object, fieldName, fieldType, features);
    }
}