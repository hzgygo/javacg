package com.hzgy.core.util;

import org.apache.commons.codec.binary.Base64;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class AESUtil {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";//默认的加密算法

    /**
     * AES 加密操作
     * @param content  待加密内容
     * @param password 加密密码
     * @return 返回Base64转码后的加密数据
     */
    public static String encryptToBase64(String content, String password) throws Exception {
        // 创建密码器
        Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
        byte[] byteContent = content.getBytes("utf-8");
        // 初始化为加密模式的密码器
        cipher.init(Cipher.ENCRYPT_MODE, generateKey(password));
        // 加密
        byte[] result = cipher.doFinal(byteContent);
        //通过Base64转码返回
        return new Base64().encodeAsString(result);
    }

    /**
     * 加密成字节数据
     * @param content 加密内容
     * @param password 密码
     * @return 返回加密后的字节数组
     */
    public static byte[] encryptToBytes(String content, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(password));
            // 加密
            return cipher.doFinal(byteContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 字节加密
     * @param byteContent 字节内容
     * @param password 密码
     * @return 返回加密后的base64字符串
     */
    public static String encryptToBase64(byte [] byteContent, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(password));
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            //通过Base64转码返回
            return new Base64().encodeAsString(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * aes加密
     * @param byteContent 加密字节数组
     * @param password 密码
     * @return 返回加密后字节数据
     */
    public static byte [] encryptToBytes(byte [] byteContent, String password) {
        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, generateKey(password));
            // 加密
            return cipher.doFinal(byteContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密操作
     * @param content 待加密内容
     * @param password 加密密码
     * @return 异常
     */
    public static String decryptToString(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
            //执行操作
            byte[] result = cipher.doFinal(new Base64().decode(content));
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 解密成字节数据
     * @param content 解密内容
     * @param password 密码
     * @return 返回解密后的字节数组
     */
    public static byte[] decryptToBytes(String content, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
            //执行操作
            return cipher.doFinal(new Base64().decode(content));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 字节解密
     * @param byteContent 字节内容
     * @param password 密码
     * @return 异常
     */
    public static String decryptToString(byte [] byteContent, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
            //执行操作
            byte[] result = cipher.doFinal(byteContent);
            return new String(result, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     *  密码加密成数组
     * @param byteContent 字节数组内容
     * @param password 密码
     * @return 字节数组
     */
    public static byte[] decryptToBytes(byte [] byteContent, String password) {
        try {
            //实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, generateKey(password));
            //执行操作
            return cipher.doFinal(byteContent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    /**
     * 生成aes秘钥
     * @param key 原始密码
     * @return Key
     * @throws Exception 异常
     */
    private static Key generateKey(String key)throws Exception{
        try{
            return new SecretKeySpec(key.getBytes(), "AES");
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }
//    /**
//     * 生成加密秘钥
//     * @return 返回秘钥对象
//     */
//    private static SecretKeySpec getSecretKey(final String password) {
//        //返回生成指定算法密钥生成器的 KeyGenerator 对象
//        KeyGenerator kg = null;
//        try {
//            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
//            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );
//            secureRandom.setSeed(password.getBytes());
////             根据密钥初始化密钥生成器
//            kg.init(128, secureRandom);
//            //AES 要求密钥长度为 128
////            kg.init(128, new SecureRandom(password.getBytes()));
//            //生成一个密钥
//            SecretKey secretKey = kg.generateKey();
//            // 转换为AES专用密钥
//            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
//        } catch (NoSuchAlgorithmException ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }

//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String dd = AESUtil.encryptToBase64("test","UITN25LMUQC436IM");
//        System.out.println("dd:"+dd);
//        String ddd = AESUtil.decryptToString(dd,"UITN25LMUQC436IM");
//        System.out.println("ddd:"+ddd);
//        String s = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUNjA6lQ1e3XjwB2ZGOuz24Le6LisReueT2RzV%0AW9ZGI2FFjrsYwAdiryTxh6KwtZKtx%2FLAuW9fe%2FO7xA0WWRTPkPOScYGexeLxDEXazDYXEOKoxtLM%0ASMBmZYQWWQZ9uiUL4QLpz4RELYK%2BNyNnmRy5Q8qtxZDVPqtkdRHhkp7GNwIDAQAB";
//        s = java.net.URLDecoder.decode(s,"utf-8");
//        System.out.println("s:" + s);
//        String s1 = AESUtil.encrypt(s, "1234");
//        System.out.println("s1:" + s1);
//        System.out.println("s2:"+AESUtil.decrypt(s1, "1234"));
//    }
}
