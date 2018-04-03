package com.duanyu.utils.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 
 * DES加解密
 * 
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class DESUtil {

    private static byte[] IV = { 0x12, 0x34, 0x56, 0x78, (byte) 0x90, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF };

    /**
     * 加密
     * 
     * @param data
     *            待加密的明文
     * @param key
     *            加密密钥
     * @return 密文
     *             异常信息
     */
    public static String encrypt(String data, String key) {
        try {
            DESKeySpec ks = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey sk = skf.generateSecret(ks);
            Cipher cip = Cipher.getInstance("DES/CBC/PKCS5Padding");// Cipher.getInstance("DES");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cip.init(Cipher.ENCRYPT_MODE, sk, ivSpec);// IV的方式
            return new String(new Base64().encode(cip.doFinal(data.getBytes("UTF-8"))));
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 解密
     * 
     * @param data 待解密的密文
     * @param key 解密密钥
     * @return 明文
     */
    public static String decrypt(String data, String key) {
        try {
            if (data == null) {
                return null;
            }
            byte[] buf = new Base64().decode(data.getBytes("UTF-8"));
            DESKeySpec dks = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec ivSpec = new IvParameterSpec(IV);
            cipher.init(Cipher.DECRYPT_MODE, securekey, ivSpec);
            return new String(cipher.doFinal(buf));
        } catch (Exception e) {
            return null;
        }
    }
}
