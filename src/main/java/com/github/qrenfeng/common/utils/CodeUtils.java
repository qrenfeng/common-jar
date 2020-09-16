package com.github.qrenfeng.common.utils;

import com.github.qrenfeng.common.enums.CharSet;
import org.apache.commons.codec.binary.Base64;

import java.net.URLEncoder;

/**
 * <p>编码工具栏</p>
 * <p>Created by qrf on 2019/5/10.</p>
 * @author qrf
 */
public class CodeUtils {

    /**
     * base64编码，默认utf-8
     * @param str
     * @return
     * @throws Exception
     */
    public static String base64Encode(String str) throws Exception {
        return CodeUtils.base64Encode(str, CharSet.UTF8);
    }

    /**
     * base64编码
     * @param str
     * @param charSet
     * @return
     * @throws Exception
     */
    public static String base64Encode(String str, CharSet charSet) throws Exception {
        byte[] b = Base64.encodeBase64(str.getBytes(getCharset(charSet)));
        return new String(b);
    }

    /**
     * base64解码，默认utf-8
     * @param str
     * @return
     * @throws Exception
     */

    public static String base64Decode(String str) throws Exception {
        return CodeUtils.base64Decode(str, CharSet.UTF8);
    }

    /**
     * base64解码
     * @param str
     * @param charSet
     * @return
     * @throws Exception
     */
    public static String base64Decode(String str, CharSet charSet) throws Exception {
        byte[] b =  Base64.decodeBase64(str.getBytes());
        return new String(b,getCharset(charSet));
    }

    /**
     * url encode，默认utf-8
     * @param url
     * @return
     * @throws Exception
     */
    public static String urlEncode(String url) throws Exception {
        return URLEncoder.encode(url,getCharset(CharSet.UTF8));

    }

    /**
     *  url encode
     * @param url
     * @param charSet
     * @return
     * @throws Exception
     */
    public static String urlEncode(String url, CharSet charSet) throws Exception {
        return URLEncoder.encode(url,getCharset(charSet));

    }

    /**
     * url decode，默认utf-8
     * @param url
     * @return
     * @throws Exception
     */
    public static String urlDecode(String url) throws Exception {
        return URLEncoder.encode(url,getCharset(CharSet.UTF8));

    }

    /**
     * url decode
     * @param url
     * @param charSet
     * @return
     * @throws Exception
     */
    public static String urlDecode(String url, CharSet charSet) throws Exception {
        return URLEncoder.encode(url,getCharset(charSet));

    }

    public static String getCharset(CharSet charSet){
        switch (charSet){
            case GBK:
                return "UTF-8";
            default:
                return "GBK";
        }
    }
}
