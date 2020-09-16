package com.github.qrenfeng.common.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * <p>rsa工具类</p>
 * <p>Created by qrf on 2019/5/30.</p>
 * @author qrf
 */
public class RSAUtils {

    /** rsa算法*/
    private static final String KEY_ALGORITHM = "RSA";
    /** rsa前面算法*/
    private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static final String PUBLIC_KEY = "RSAPublicKey";

    public static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * rsa签名
     * @param content
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(String content, String privateKey) throws Exception{
        PKCS8EncodedKeySpec pripkcs8 	= new PKCS8EncodedKeySpec( Base64.decodeBase64(privateKey) );
        KeyFactory keyf 				= KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey priKey 				= keyf.generatePrivate(pripkcs8);

        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

        signature.initSign(priKey);
        signature.update( content.getBytes() );

        byte[] signed = signature.sign();

        return Base64.encodeBase64String(signed);
    }

    /**
     * rsa签名
     * @param params
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String sign(Map<String,String> params, String privateKey) throws Exception{
        String content = createLinkString(paramsFilter(params));
        return RSAUtils.sign(content, privateKey);
    }

    /**
     * rsa验证签名
     * @param content
     * @param sign
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static boolean verify(String content,String sign,String publicKey) throws Exception{
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        byte[] encodedKey = Base64.decodeBase64(publicKey);
        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
        signature.initVerify(pubKey);
        signature.update( content.getBytes() );
        return signature.verify( Base64.decodeBase64(sign));
    }

    /**
     * rsa验签
     * @param params
     * @param sign
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static boolean verify(Map<String,String> params,String sign,String publicKey) throws Exception{
        String content = createLinkString(paramsFilter(params));
        return RSAUtils.verify(content, sign, publicKey);
    }


    /**
     * 生成rsa公私钥对
     * @return
     * @throws Exception
     */
    public static Map<String, String> initKey() throws Exception {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);
        //通过对象 KeyPairGenerator 获取对象KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();

        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        //公私钥对象存入map中
        Map<String, String> keyMap = new HashMap<>(16);
        keyMap.put(PUBLIC_KEY, Base64.encodeBase64String(publicKey.getEncoded()));
        keyMap.put(PRIVATE_KEY, Base64.encodeBase64String(privateKey.getEncoded()));
        return keyMap;
    }

    /**
     * rsa加密
     * @param str
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String encrypt(String str, String publicKey) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * rsa解密
     * @param str
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(KEY_ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }

    /**
     * 参数过滤
     * @param params 参数
     * @return 过滤参数
     */
    private static Map<String, String> paramsFilter(Map<String, String> params) {
        HashMap result = new HashMap(16);
        if(params != null && params.size() > 0) {
            Iterator iterator = params.keySet().iterator();

            while(iterator.hasNext()) {
                String key = (String)iterator.next();
                String value = (String)params.get(key);
                if(value != null && !"".equals(value) && !"sign".equalsIgnoreCase(key)) {
                    result.put(key, value);
                }
            }

            return result;
        } else {
            return result;
        }
    }

    /**
     * 拼接字符
     * @param params 参数
     * @return 拼接字符
     */
    private static String createLinkString(Map<String, String> params) {
        ArrayList keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        StringBuilder prestr = new StringBuilder();

        for(int i = 0; i < keys.size(); ++i) {
            String key = (String)keys.get(i);
            String value = (String)params.get(key);
            prestr.append(key);
            prestr.append("=");
            prestr.append(value);
            prestr.append("&");
        }

        String result = prestr.toString();
        if(result.length() > 1) {
            result = result.substring(0, prestr.length() - 1);
        }
        return result;
    }
}
