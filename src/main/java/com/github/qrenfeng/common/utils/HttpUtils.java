package com.github.qrenfeng.common.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;

/**
 * <p>http工具类</p>
 * @author qrf
 * @date 2021/10/11
 */
@Slf4j
public class HttpUtils {

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    public static String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * post 表单
     * @param url
     * @param headers
     * @param body
     * @return
     */
    public static String postForm(String url,Map<String,String> headers, Map<String,String> body) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (String key: body.keySet()){
            formBuilder.add(key, body.get(key));
        }
        RequestBody requestBody = formBuilder.build();
        return post(url, headers,requestBody);
    }

    /**
     * post json
     * @param url
     * @param headers
     * @param body
     * @return
     */
    public static String postJson(String url,Map<String,String> headers, Object body){
        MediaType jsonType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(jsonType, JSON.toJSONString(body));
        return post(url, headers, requestBody);
    }

    /**
     * post提交
     * @param url
     * @param headers
     * @param requestBody
     * @return
     */
    private static String post(String url, Map<String,String> headers,RequestBody requestBody){
        try {
            log.debug("req:{}-{}", url, requestBody.contentLength());
            Headers.Builder headerBuilder = new Headers.Builder();
            if (headers!=null){
                for (String key: headers.keySet()){
                    String val = headers.get(key);
                    log.info("header:{}-{}", key, val);
                    headerBuilder.add(key, headers.get(key));
                }
            }
            Request request = new Request.Builder()
                    .url(url)
                    .headers(headerBuilder.build())
                    .post(requestBody)
                    .build();
            Response response = okHttpClient.newCall(request).execute();
            log.debug("resp:{}", response);
            String resp = response.body().string();
            return resp;
        }catch (IOException e){
            log.error("http:error", e);
            return null;
        }
    }
}
