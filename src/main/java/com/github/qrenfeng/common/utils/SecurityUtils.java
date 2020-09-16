package com.github.qrenfeng.common.utils;

import com.github.qrenfeng.common.enums.CharSet;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;

/**
 * <p>安全工具集合</p>
 * <p>Created by qrf on 2019/5/10.</p>
 * @author qrf
 */
public class SecurityUtils {

    private static final String KEY_ALGORITHM = "AES";

    private static final String BC_ALGORITHM = "BC";

    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * md5
     * @param str
     * @return
     */
    public static String md5(String str){
        return DigestUtils.md5Hex(str);
    }

    /**
     * sha1
     * @param str
     * @return
     */
    public static String sha1(String str){
        return DigestUtils.sha1Hex(str);
    }

    /**
     * sha2
     * @param str
     * @return
     */
    public static String sha2(String str){
        return DigestUtils.sha256Hex(str);
    }

    /**
     * aes256加密，默认utf-8
     * @param str
     * @param key
     * @return
     * @throws Exception
     */
    public static String aes256Enc(String str, String key) throws Exception {
        //通过Base64转码返回
        return SecurityUtils.aes256Enc(str, key, CharSet.UTF8);
    }

    /**
     * aes256加密
     * @param content
     * @param key
     * @param charSet
     * @return
     * @throws Exception
     */
    public static String aes256Enc(String str, String key, CharSet charSet) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM, BC_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] result = cipher.doFinal(str.getBytes(CodeUtils.getCharset(charSet)));
        return Base64.encodeBase64String(result);
    }

    /**
     * aes256解密,默认utf-8
     * @param encStr
     * @param key
     * @return
     * @throws Exception
     */
    public static String aes256Dec(String encStr, String key) throws Exception {
        //通过Base64转码返回
        return SecurityUtils.aes256Dec(encStr, key, CharSet.UTF8);
    }


    /**
     * aes256解密
     *
     * @param encStr
     * @param key
     * @param charSet
     * @return
     * @throws Exception
     */
    public static String aes256Dec(String encStr, String key, CharSet charSet) throws Exception {
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM, BC_ALGORITHM);
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] result = cipher.doFinal(Base64.decodeBase64(encStr));
        return new String(result,CodeUtils.getCharset(charSet));
    }
}
