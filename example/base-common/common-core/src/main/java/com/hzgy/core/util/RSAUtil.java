package com.hzgy.core.util;


import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAUtil {

    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    public static final String RSA = "RSA/ECB/PKCS1Padding";

    /**
     * 获取公钥
     *
     * @param pukPath 公钥文件路径
     * @return 返回公钥对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String pukPath) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(pukPath);
        Certificate crt = cf.generateCertificate(in);
        PublicKey publicKey = crt.getPublicKey();
        return publicKey;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static PublicKey publicKeyByStr(String publicKeyStr) throws Exception {
        byte[] buffer = Base64.getDecoder().decode(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 根据公钥字节数组，获取公钥对象
     * @param publicKeyByte 公钥字节数组
     * @return  PublicKey
     * @throws Exception 异常
     */
    public static PublicKey publicKeyByByte(byte publicKeyByte []) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyByte);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * 根据私钥字符串获取私钥对象
     * @param privateKeyStr 私钥字符串
     * @return PrivateKey
     * @throws Exception 异常
     */
    public PrivateKey privateKeyByStr(String privateKeyStr) throws Exception {
        byte[] buffer = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    /**
     * 获取私钥
     *
     * @param alias     别名
     * @param storeType 存储类型
     * @param password  密码
     * @param storePath 存储路径
     * @return 返回私钥
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey(String alias, String storeType, String password, String storePath) throws Exception {
        storeType = null == storeType ? KeyStore.getDefaultType() : storeType;
        KeyStore keyStore = KeyStore.getInstance(storeType);
        InputStream is = new FileInputStream(storePath);
        char[] pw = password.toCharArray();
        keyStore.load(is, pw);
        //由密钥库获取密钥的两种方式
        KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(pw));
        return pkEntry.getPrivateKey();
    }

    /**
     * 私钥签名
     *
     * @param prk  私钥对象
     * @param data 签名数据
     * @return 返回签名字节数组
     * @throws Exception 异常
     */
    public static byte[] sign(PrivateKey prk, byte[] data) throws Exception {
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(prk);
        sig.update(data);
        return sig.sign();
    }

    /**
     * 验证签名
     *
     * @param puk  公钥对象
     * @param data 验证数据字节数组
     * @param sign 签名字节数据
     * @return 是否通过
     * @throws Exception 异常
     */
    public static boolean verify(PublicKey puk, byte[] data, byte[] sign) throws Exception {
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(puk);
        sig.update(data);
        return sig.verify(sign);
    }


    /**
     * 公钥加密
     *
     * @param publicKey 公钥对象
     * @param plaintext 加密字符串
     * @return 返回加密后字节数组
     * @throws Exception 异常
     */
    public static byte[] encrypt(PublicKey publicKey, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(plaintext);
    }

    /**
     * 公钥加密
     *
     * @param publicKey 公钥对象
     * @param plaintext 加密字符串
     * @return 返回加密后字节数组
     * @throws Exception 异常
     */
    public static String encryptToString(PublicKey publicKey, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte [] b = cipher.doFinal(plaintext);
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * 私钥加密
     *
     * @param privateKey 私钥对象
     * @param plaintext  加密字符串
     * @return 返回加密后字节数组
     * @throws Exception 异常
     */
    public static byte[] encrypt(PrivateKey privateKey, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(plaintext);
    }

    /**
     * 私钥加密
     *
     * @param privateKey 私钥对象
     * @param plaintext  加密字符串
     * @return 返回加密后字节数组
     * @throws Exception 异常
     */
    public static String encryptToString(PrivateKey privateKey, byte[] plaintext) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte [] b = cipher.doFinal(plaintext);
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥对象
     * @param encrypted  待解密字节数组
     * @return 返回解密后字节数组
     * @throws Exception 异常
     */
    public static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encrypted);
    }

    /**
     * 私钥解密
     *
     * @param privateKey 私钥对象
     * @param encrypted  待解密字节数组
     * @return 返回解密后字节数组
     * @throws Exception 异常
     */
    public static String decryptToString(PrivateKey privateKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte [] b = cipher.doFinal(encrypted);
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * 公钥解密
     *
     * @param publicKey 公钥对象
     * @param encrypted 待解密字节数组
     * @return 返回解密后字节数组
     * @throws Exception 异常
     */
    public static byte[] decrypt(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(encrypted);
    }

    /**
     * 公钥解密
     *
     * @param publicKey 公钥对象
     * @param encrypted 待解密字节数组
     * @return 返回解密后字节数组
     * @throws Exception 异常
     */
    public static String decryptToString(PublicKey publicKey, byte[] encrypted) throws Exception {
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte [] b = cipher.doFinal(encrypted);
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * 生成key文件
     *
     * @param key      秘钥对象
     * @param filePath 文件路径
     * @throws Exception 异常
     */
    public static void createKeyFile(Object key, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(key);
        oos.flush();
        oos.close();
    }

    /**
     * 获取秘钥对象
     *
     * @param fileName 文件名称
     * @return key
     * @throws Exception 异常
     */
    public static Key getKey(String fileName) throws IOException {
        Key key = null;
        ObjectInputStream ois = null;
        try {
            //将文件中的私钥对象读出
            ois = new ObjectInputStream(new FileInputStream(fileName));
            key = (Key) ois.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (ois != null) {
                ois.close();
            }
        }
        return key;
    }

//    public static void main(String[] args) throws Exception {
//        String rootath = "/Users/zhuguiqiu/java-workspace/tyzybaas/target/config";
//
//        PublicKey rsaPublicKey = null;
//        PrivateKey rsaPrivateKey = null;
//        try {
//            //KeyTool中生成KeyStore时设置的alias
//            String alias = "tyzyssl";
//            //KeyTool中生成KeyStore时设置的storetype
//            String type = "JCEKS";
//            //KeyTool中生成KeyStore时设置的storepass
//            String pwd = "123456789";
//            //KeyTool中已生成的KeyStore文件
//            String keystorePath = rootath + File.separator + "security" + File.separator + "tyzy.jks";
//            //KeyTool中导出的公钥文件
//            String pukPath = rootath + File.separator + "security" + File.separator + "tyzy.crt";
//            rsaPublicKey = RSAUtil.getPublicKey(pukPath);
//            rsaPrivateKey = RSAUtil.getPrivateKey(alias, type, pwd, keystorePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //生成此机构的公私钥文件
//        String configRootPath = rootath + File.separator + "security";
//        //创建文件目录
//        File parentFile = new File(configRootPath);
//        if (!parentFile.exists()) {
//            parentFile.mkdirs();
//        }
//        //公钥文件
//        String publicKeyFile = configRootPath + File.separator + "public.key";
//        //私钥文件
//        String privateKeyFile = configRootPath + File.separator + "private.key";
//        try {
//            //存储公钥
//            RSAUtil.createKeyFile(rsaPublicKey, publicKeyFile);
//            //存储私钥
//            RSAUtil.createKeyFile(rsaPrivateKey, privateKeyFile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    //********************main函数：加密解密和签名验证*********************
//    public static void main(String[] args) throws Exception {
//
////        String publicKey1 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCUNjA6lQ1e3XjwB2ZGOuz24Le6LisReueT2RzV\nW9ZGI2FFjrsYwAdiryTxh6KwtZKtx/LAuW9fe/O7xA0WWRTPkPOScYGexeLxDEXazDYXEOKoxtLM\nSMBmZYQWWQZ9uiUL4QLpz4RELYK+NyNnmRy5Q8qtxZDVPqtkdRHhkp7GNwIDAQAB";
////        String appid = "c7JCuxAg1qX/yIZ3Zp7swy90CaDOk4GDyZvrMvPZ5S9uikFij6uOUTiBiCk6bFwsNuTW6Ght3tDw\n+S8tfGzEiUPY+GMATmRNeF8qZaRUmNjfot7LJP52OhFsxRQUcUBGtyKnzo7zdHIw/YMt1dgRk38x\nZY2YkuuYHHJH6cTjHNk=";
////        String mw = "2Zf7RRVRU+TX4SJeRiwpjf6L62qFfmdUZjBe68y2FJFs7xXVVg5UInsN/eEa2UzuNhKA3z4RX18sBaPoDLtBF8vCLZB5e8E8vyQKu2W4wtY7n7kei67mybyN/AE8oRho0QDsFzvbz1+6yktaxFZi0HHqNhF3fk617Eu99i1ZbQ1Os3FdAymDE2UYcclAXbS0QFkRz76ycmuy0H/Ybt8CzXJ1kl/K+agRRuQ7w+Lll0uG3VkGI+AsnarwoRf/myoEYNb2OqCMhesUYPH3YRYjUpw+qIpUagnD9RaqM0fspEI5jpeEPALBpDKilwtkEf3R";
////
////        String str_plaintext = "UITN25LMUQC436IM";
////        String kp = "/Users/zhuguiqiu/java-workspace/tyzybaas/target/config/security/public.key";
////        PublicKey publicKey = (PublicKey)getKey(kp);
////        String storePath = "/Users/zhuguiqiu/java-workspace/tyzybaas/target/config/security/private.key"; // KeyTool中已生成的KeyStore文件
////        PrivateKey privateKey = (PrivateKey)getKey(storePath);
////        String pw  = "FFYO38WtlKwfsqdGRnpfkQC1fzgSh3nPQo/xeBJoc5sgG69miAwhBi8QnmTkHx5GFc7CdTtUIDdHSG27icrPUl4Qb35cCpk2wfNEMeS1lN6gSVumUDZwEtGB0aSTcxS348ej3WHbgzmGM0Np0iplNEAFCjSshRLLI2dJBXjJ+Wc=";
////        byte [] aa = new BASE64Decoder().decodeBuffer(pw);
////        byte [] dd = RSAUtil.decrypt(privateKey,aa);
////        String sdsd64 = new String(dd);
////        System.out.println("pwd:"+sdsd64);
////        PublicKey serverPuk = RSAUtil.publicKeyByStr(publicKey1);
////        String  ff = "V3B588M9ZV1eJiQxWAeEB%2BI466PVretqIFsYZxGG18hvMaudp4tiU1a508dQW33vtDYhgvIyUfpqulJT6HdxaBDRrRK%2FERVJyuG5Gmzd1ZUkBytRao3WcC9OL6Y5sF9KLTgy5ICK8ytvk%2BDlUI0OPTWh2jRULoptCheVaqYxL8k%3D";
////        ff  =  java.net.URLDecoder.decode(ff,"utf-8");
////        byte[] ffd = new BASE64Decoder().decodeBuffer(ff);
////        byte[] sdsd = RSAUtil.decrypt(privateKey,ffd);
////        String sdsd64 = new String(sdsd);
////        System.out.println("pwd:"+sdsd64);
////
////        byte[] aeskeyBytes = RSAUtil.encrypt(serverPuk,str_plaintext.getBytes());
////        String aeskey64 = new BASE64Encoder().encode(aeskeyBytes);
////        System.out.println("pwd:"+aeskey64);
//////        byte [] dd = RSAUtil.encrypt(publicKey,str_plaintext.getBytes("utf-8"));
//////        String dds= Base64.encodeBase64String(dd);
//////        System.out.println("加密后："+ dds);
////
////        System.err.println("明文："+str_plaintext);
////        String alias = "tyzyssl"; // KeyTool中生成KeyStore时设置的alias
////        String storeType = "JCEKS"; // KeyTool中生成KeyStore时设置的storetype
////        String password = "123456789"; // KeyTool中生成KeyStore时设置的storepass
////        PrivateKey privateKey = getPrivateKey(alias,storeType,password,storePath);
////        byte[] pwdb = RSAUtil.decrypt(privateKey,mw.getBytes());
////        String pwd = new String(pwdb);
////        System.out.println("解密结果:"+pwd);
////        byte[] bt_cipher = encrypt(privateKey,str_plaintext.getBytes());
////        String b63jm= Base64.encodeBase64String(bt_cipher);
////        System.out.println("加密后："+ b63jm);
////        String cpuk = "2Zf7RRVRU%2BTX4SJeRiwpjf6L62qFfmdUZjBe68y2FJFs7xXVVg5UInsN%2FeEa2UzuNhKA3z4RX18sBaPoDLtBF8vCLZB5e8E8vyQKu2W4wtY7n7kei67mybyN%2FAE8oRho0QDsFzvbz1%2B6yktaxFZi0HHqNhF3fk617Eu99i1ZbQ1Os3FdAymDE2UYcclAXbS0QFkRz76ycmuy0H%2FYbt8CzXJ1kl%2FK%2BagRRuQ7w%2BLll0uG3VkGI%2BAsnarwoRf%2FmyoEYNb2OqCMhesUYPH3YRYjUpw%2BqIpUagnD9RaqM0fspEI5jpeEPALBpDKilwtkEf3R";
////        String  aa = "MvVEimJ%2B2c4Q74sYeGUq9zHmG%2BpFYa945xDUrLpNkBs2hZk%2Bba3fstiHF2CFUYMAoHC8fQ7mWENMsODUUyw6LNnxDaDLNAaloAWWwFN5RJU%3D";
////        aa  =  java.net.URLDecoder.decode(aa,"utf-8");
////        cpuk  =  java.net.URLDecoder.decode(cpuk,"utf-8");
////        String  ad =  AESUtil.decryptToString(aa,str_plaintext);
////        System.out.println("解密结果:"+ad);
////        String  cpukd  =  AESUtil.decryptToString(cpuk,str_plaintext);
////        System.out.println("解密结果cpukd:"+cpukd);
////        System.out.println("解密结果cpukd:"+java.net.URLDecoder.decode(cpukd,"utf-8"));
////        byte[] bt_original = decrypt(publicKey,Base64.decodeBase64(b63jm));
////        String str_original = new String(bt_original);
////        System.out.println("解密结果:"+str_original);
//
//        String str = "{\"amount\":\"0.00\",\"code\":\"212\",\"description\":\"\\u8d3a\\u5f50\\u5251\\u8d44\\u4ea7\"}";
//        String ss = "YYnluxxA8eBtK5Pf2rvKC/74fxMaLB6uvjkAtJQVzVlgWJKzoFkHI26YVHq1AJKbzIYAT+W33mc3loo3lrya5Bkw7W6v+/lJnSXFb/lSAjkgN3mtyWvgTQLM9qfU69xXg9wYCcpLtNDMmBVn2ZhHxCDMSdd2R1qSBQJUU681GNs=";
////        ss  =  java.net.URLDecoder.decode(ss,"utf-8");
//        byte [] sss = Base64.getDecoder().decode(ss);
//        System.out.println("ss："+ss);
////        System.err.println("\n原文:"+str);
////        byte[] signature=sign(privateKey,str.getBytes());
////        System.out.println("产生签名："+Base64.encodeBase64String(signature));
//        String dataJsonSha256 = SHAUtil.getSHA256(str);
//        System.out.println("dataJsonSha256："+dataJsonSha256);
//        String  ccpk = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3jadhZKOyvv70e5oCSu0vSS3e\n" +
//                "mDhWDWkMIIm1QeTR8Jh02SlWHyesYy3sekj9cjrAhQCsVO1kTIsiDKk750PkcThT\n" +
//                "jS02hYWq/697xtSqhzSoSsJCnXaixE3/EnFiFadJNMf0ySe4iPo+nhXAEQj8m1nw\n" +
//                "yed3LWshKzwEHoyGvQIDAQAB";
//        PublicKey clientPuk = RSAUtil.publicKeyByStr(ccpk);
//        boolean status=verify(clientPuk,dataJsonSha256.getBytes(Constants.ENCODING_UTF), sss);
//        System.out.println("验证情况："+status);
//    }
}