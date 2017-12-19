package com.freestyledash.curryx.common.util.encryption;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES加密工具
 *
 * @author zhangyanqi
 * @since 1.0 2017/11/8
 */
public class DESEncryptUtil implements EncryptUtil {

    private byte[] key = null;
    private String keyGenName = null;

    {
        key = new byte[]{97, -113, -68, 93, 127, -17, 49, 67};  //秘钥
        keyGenName = "DES";
    }

    /**
     * 加密
     *
     * @param content 被加密的内容
     * @return 加密结果
     */
    @Override
    public byte[] encrypt(byte[] content) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, keyGenName);//恢复密钥
        Cipher cipher = Cipher.getInstance(keyGenName);//Cipher完成加密或解密工作类
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);//对Cipher初始化，加密模式
        byte[] cipherByte = cipher.doFinal(content);//加密data
        return cipherByte;
    }

    /**
     * 解密
     *
     * @param content 被解密内容
     * @return 解密结果
     */
    @Override
    public byte[] decode(byte[] content) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, keyGenName);//恢复密钥
        Cipher cipher = Cipher.getInstance(keyGenName);//Cipher完成加密或解密工作类
        cipher.init(Cipher.DECRYPT_MODE, secretKey);//对Cipher初始化，解密模式
        byte[] origin = cipher.doFinal(content);//解密data
        return origin;
    }

}
