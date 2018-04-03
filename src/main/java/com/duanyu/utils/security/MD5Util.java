package com.duanyu.utils.security;

import java.security.MessageDigest;

/**
 * 
 * md5加密
 * 
 * @author 作者 E-mail <a href="mailto:szg@51box.cn">石志刚</a>
 * @version 1.0.0
 */
public class MD5Util {
    private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    /**
     * 
     * 转换字节数组为16进制字串
     * 
     * @param b
     *            字节数组
     * 
     * @return 16进制字串
     */

    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String compile(String origin) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        }

        catch (Exception ex) {

        }

        return resultString;

    }

    /**
     * MD5 摘要计算(byte[]).
     * 
     * @param src
     *            byte[]
     * @throws Exception
     * @return byte[] 16 bit digest
     */
    public static byte[] md5Digest(byte[] src) throws Exception {
        MessageDigest alg = MessageDigest.getInstance("MD5"); // MD5 is 16 bit
                                                              // message digest
        return alg.digest(src);
    }

    /**
     * MD5 摘要计算(String).
     * 
     * @param src
     *            String
     * @throws Exception
     * @return String
     */
    public static String md5Digest(String src) throws Exception {
        return byteArrayToHexString(md5Digest(src.getBytes()));
    }
}
