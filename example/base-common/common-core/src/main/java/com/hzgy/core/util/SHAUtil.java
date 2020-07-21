package com.hzgy.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {

    private final static String SHA_256 = "SHA-256";

    private final static String ENCODING = "UTF-8";

    /**
     * 利用java原生的摘要实现SHA256加密
     *
     * @param source 加密前的字符串
     * @return 加密后的字符串
     */
    public static String getSHA256(String source, String salt) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            //将原始字符串与盐连接在一起
            //如果有需要可以增加其他固定字符和加密算法
            source = source + salt;
            messageDigest = MessageDigest.getInstance(SHA_256);
            messageDigest.update(source.getBytes(ENCODING));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 对字符串进行sha256编码
     *
     * @param source
     * @return
     */
    public static String getSHA256(String source) {
        MessageDigest messageDigest;
        String encodeStr = "";
        try {
            //将原始字符串与盐连接在一起
            //如果有需要可以增加其他固定字符和加密算法
            messageDigest = MessageDigest.getInstance(SHA_256);
            messageDigest.update(source.getBytes(ENCODING));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     *
     * @param bytes 摘要字节数组
     * @return 返回十六进制字符串
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
