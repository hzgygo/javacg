package com.hzgy.core.util;

/**
 * @Auther: zhuguiqiu
 * @Date: 2018/8/31 14:11
 */

import java.security.MessageDigest;
import java.security.Security;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

public class RipeMDUtil {

    /**
     * RipeMD128消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return byte[] 消息摘要
     */
    public static byte[] encodeRipeMD128(byte[] data) throws Exception {
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD128");
        //执行消息摘要
        return md.digest(data);

    }

    /**
     * RipeMD128Hex消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return String 消息摘要
     **/
    public static String encodeRipeMD128Hex(byte[] data) throws Exception {
        //执行消息摘要
        byte[] b = encodeRipeMD128(data);
        //做十六进制的编码处理
        return new String(Hex.encode(b));
    }

////////////////////RipeMD160消息摘要处理///////////////////////////

    /**
     * RipeMD160消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return byte[] 消息摘要
     */
    public static byte[] encodeRipeMD160(byte[] data) throws Exception {
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD160");
        //执行消息摘要
        return md.digest(data);

    }

    /**
     * RipeMD160Hex消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return String 消息摘要
     **/
    public static String encodeRipeMD160Hex(byte[] data) throws Exception {
        //执行消息摘要
        byte[] b = encodeRipeMD160(data);
        //做十六进制的编码处理
        return new String(Hex.encode(b));
    }
////////////////////RipeMD256消息摘要处理///////////////////////////

    /**
     * RipeMD256消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return byte[] 消息摘要
     */
    public static byte[] encodeRipeMD256(byte[] data) throws Exception {
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD256");
        //执行消息摘要
        return md.digest(data);

    }

    /**
     * RipeMD256Hex消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return String 消息摘要
     **/
    public static String encodeRipeMD256Hex(byte[] data) throws Exception {
        //执行消息摘要
        byte[] b = encodeRipeMD256(data);
        //做十六进制的编码处理
        return new String(Hex.encode(b));
    }

////////////////////RipeMD320消息摘要处理///////////////////////////

    /**
     * RipeMD320消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return byte[] 消息摘要
     */
    public static byte[] encodeRipeMD320(byte[] data) throws Exception {
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("RipeMD320");
        //执行消息摘要
        return md.digest(data);

    }

    /**
     * RipeMD320Hex消息摘要
     *
     * @param data 待处理的消息摘要数据
     * @return String 消息摘要
     **/
    public static String encodeRipeMD320Hex(byte[] data) throws Exception {
        //执行消息摘要
        byte[] b = encodeRipeMD320(data);
        //做十六进制的编码处理
        return new String(Hex.encode(b));
    }
///////////////////////////////HmacRipeMD-BouncyCastle支持的实现//////////////////////////////////

    /**
     * 初始化HmacRipeMD128的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacRipeMD128Key() throws Exception {

        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacRipeMD128");
        //产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacRipeMD128消息摘要
     *
     * @param data 待做摘要处理的数据
     * @param key  密钥
     * @return byte[] 消息摘要
     */
    public static byte[] encodeHmacRipeMD128(byte[] data, byte[] key) throws Exception {
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacRipeMD128");
        //实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化Mac
        mac.init(secretKey);
        //执行消息摘要处理
        return mac.doFinal(data);
    }

    /**
     * HmacRipeMD128Hex消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @param key  密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacRipeMD128Hex(byte[] data, byte[] key) throws Exception {
        //执行消息摘要处理
        byte[] b = encodeHmacRipeMD128(data, key);
        //做十六进制转换
        return new String(Hex.encode(b));
    }

///////////////////////////////HmacRipeMD-BouncyCastle支持的实现//////////////////////////////////

    /**
     * 初始化HmacRipeMD160的密钥
     *
     * @return byte[] 密钥
     */
    public static byte[] initHmacRipeMD160Key() throws Exception {

        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //初始化KeyGenerator
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacRipeMD160");
        //产生密钥
        SecretKey secretKey = keyGenerator.generateKey();
        //获取密钥
        return secretKey.getEncoded();
    }

    /**
     * HmacRipeMD160消息摘要
     *
     * @param data 待做摘要处理的数据
     * @param key  密钥
     * @return byte[] 消息摘要
     */
    public static byte[] encodeHmacRipeMD160(byte[] data, byte[] key) throws Exception {
        //加入BouncyCastleProvider的支持
        Security.addProvider(new BouncyCastleProvider());
        //还原密钥，因为密钥是以byte形式为消息传递算法所拥有
        SecretKey secretKey = new SecretKeySpec(key, "HmacRipeMD160");
        //实例化Mac
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        //初始化Mac
        mac.init(secretKey);
        //执行消息摘要处理
        return mac.doFinal(data);
    }

    /**
     * HmacRipeMD160Hex消息摘要
     *
     * @param data 待做消息摘要处理的数据
     * @param key  密钥
     * @return byte[] 消息摘要
     */
    public static String encodeHmacRipeMD160Hex(byte[] data, byte[] key) throws Exception {
        //执行消息摘要处理
        byte[] b = encodeHmacRipeMD160(data, key);
        //做十六进制转换
        return new String(Hex.encode(b));
    }

    /***
     *
     * 进行消息摘要长度的处理
     * @param args
     * @throws Exception
     */
//    public static void main(String[] args) throws Exception {
//
//        String str = "RIPEMD消息摘要";
//        System.out.println("原文：" + str);
//        byte[] data1 = RipeMDUtil.encodeRipeMD128(str.getBytes());
//        String data1hex = RipeMDUtil.encodeRipeMD128Hex(str.getBytes());
//        System.out.println("RipeMD128的消息摘要算法值：" + data1.toString());
//        System.out.println("RipeMD128的十六进制消息摘要算法值：" + data1hex);
//        System.out.println();
//
//        byte[] data2 = RipeMDUtil.encodeRipeMD160(str.getBytes());
//        String data2hex = RipeMDUtil.encodeRipeMD160Hex(str.getBytes());
//        System.out.println("RipeMD160的消息摘要算法值：" + data2.toString());
//        System.out.println("RipeMD160的十六进制消息摘要算法值：" + data2hex);
//        System.out.println();
//
//        byte[] data3 = RipeMDUtil.encodeRipeMD256(str.getBytes());
//        String data3hex = RipeMDUtil.encodeRipeMD256Hex(str.getBytes());
//        System.out.println("RipeMD256的消息摘要算法值：" + data3.toString());
//        System.out.println("RipeMD256的十六进制消息摘要算法值：" + data3hex);
//        System.out.println();
//
//        byte[] data4 = RipeMDUtil.encodeRipeMD320(str.getBytes());
//        String data4hex = RipeMDUtil.encodeRipeMD320Hex(str.getBytes());
//        System.out.println("RipeMD320的消息摘要算法值：" + data4.toString());
//        System.out.println("RipeMD320的十六进制消息摘要算法值：" + data4hex);
//        System.out.println();
//
//        System.out.println("================以下的算法支持是HmacRipeMD系列，现阶段只有BouncyCastle支持=======================");
//        //初始化密钥
//        byte[] key5 = RipeMDUtil.initHmacRipeMD128Key();
//        //获取摘要信息
//        byte[] data5 = RipeMDUtil.encodeHmacRipeMD128(str.getBytes(), key5);
//        String datahex5 = RipeMDUtil.encodeHmacRipeMD128Hex(str.getBytes(), key5);
//        System.out.println("Bouncycastle HmacRipeMD128的密钥:" + key5.toString());
//        System.out.println("Bouncycastle HmacRipeMD128算法摘要：" + data5.toString());
//        System.out.println("Bouncycastle HmacRipeMD128Hex算法摘要：" + datahex5.toString());
//        System.out.println();
//
//        //初始化密钥
//        byte[] key6 = RipeMDUtil.initHmacRipeMD160Key();
//        //获取摘要信息
//        byte[] data6 = RipeMDUtil.encodeHmacRipeMD160(str.getBytes(), key6);
//        String datahex6 = RipeMDUtil.encodeHmacRipeMD160Hex(str.getBytes(), key6);
//        System.out.println("Bouncycastle HmacRipeMD160的密钥:" + key6.toString());
//        System.out.println("Bouncycastle HmacRipeMD160算法摘要：" + data6.toString());
//        System.out.println("Bouncycastle HmacRipeMD160Hex算法摘要：" + datahex6.toString());
//        System.out.println();
//    }
}
