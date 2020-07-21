package com.hzgy.core.util;

/**
 * @Auther: zhuguiqiu
 * @Date: 2018/8/30 11:54
 */

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSAEncrypt {

    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;

    /**
     * 字节数据转字符串专用集合
     */
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    /**
     * 获取私钥
     *
     * @return 当前的私钥对象
     */
    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取公钥
     *
     * @return 当前的公钥对象
     */
    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * 随机生成密钥对
     */
    public void genKeyPair() {
        KeyPairGenerator keyPairGen = null;
        try {
            keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
            this.publicKey = (RSAPublicKey) keyPair.getPublic();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件中输入流中加载公钥
     *
     * @param in 公钥输入流
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPublicKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e) {
            throw new Exception("公钥输入流为空");
        }
    }


    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public void loadPublicKey(String publicKeyStr) throws Exception {
        try {
            Base64 base64Decoder = new Base64 ();
            byte[] buffer = base64Decoder.decode(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            this.publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("公钥非法");
        }catch (NullPointerException e) {
            throw new Exception("公钥数据为空");
        }
    }

    /**
     * 从文件中加载私钥
     * @param in 私钥文件名
     */
    public void loadPrivateKey(InputStream in) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }
            loadPrivateKey(sb.toString());
        } catch (IOException e) {
            throw new Exception("私钥数据读取错误");
        } catch (NullPointerException e) {
            throw new Exception("私钥输入流为空");
        }
    }

    public void loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            Base64  base64Decoder = new Base64 ();
            byte[] buffer = base64Decoder.decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            this.privateKey = (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e) {
            throw new Exception("私钥非法");
        }catch (NullPointerException e) {
            throw new Exception("私钥数据为空");
        }
    }

    /**
     * 加密过程
     *
     * @param publicKey     公钥
     * @param plainTextData 明文数据
     * @return 加密后二进制数组
     * @throws Exception 加密过程中的异常信息
     */
    public byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception {
        if (publicKey == null) {
            throw new Exception("加密公钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(plainTextData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此加密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("加密公钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("明文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("明文数据已损坏");
        }
    }

    /**
     * 解密过程
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return 明文
     * @throws Exception 解密过程中的异常信息
     */
    public byte[] decrypt(RSAPrivateKey privateKey, byte[] cipherData) throws Exception {
        if (privateKey == null) {
            throw new Exception("解密私钥为空, 请设置");
        }
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA", new BouncyCastleProvider());
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(cipherData);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此解密算法");
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            throw new Exception("解密私钥非法,请检查");
        } catch (IllegalBlockSizeException e) {
            throw new Exception("密文长度非法");
        } catch (BadPaddingException e) {
            throw new Exception("密文数据已损坏");
        }
    }


    /**
     * 字节数据转十六进制字符串
     *
     * @param data 输入数据
     * @return 十六进制内容
     */
    public static String byteArrayToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0) >>> 4]);
            //取出字节的低四位 作为索引得到相应的十六进制标识符
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);
            if (i < data.length - 1) {
                stringBuilder.append(' ');
            }
        }
        return stringBuilder.toString();
    }

//    private static PrivateKey getPrivateKeyFromStore() throws Exception {
//        String alias = "test"; // KeyTool中生成KeyStore时设置的alias
//        String storeType = "JCEKS"; // KeyTool中生成KeyStore时设置的storetype
//        char[] pw = "5201314".toCharArray(); // KeyTool中生成KeyStore时设置的storepass
//        String storePath = "F:/Key/test.store"; // KeyTool中已生成的KeyStore文件
//        storeType = null == storeType ? KeyStore.getDefaultType() : storeType;
//        KeyStore keyStore = KeyStore.getInstance(storeType);
//        InputStream is = new FileInputStream(storePath);
//        keyStore.load(is, pw);
//        // 由密钥库获取密钥的两种方式
//        // KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry(alias, new KeyStore.PasswordProtection(pw));
//        // return pkEntry.getPrivateKey();
//        return (PrivateKey) keyStore.getKey(alias, pw);
//    }

//    private static PublicKey getPublicKeyFromCrt() throws CertificateException, FileNotFoundException {
//        String crtPath = "F:/Key/test.crt"; // KeyTool中已生成的证书文件
//        CertificateFactory cf = CertificateFactory.getInstance("X.509");
//        FileInputStream in = new FileInputStream(crtPath);
//        Certificate crt = cf.generateCertificate(in);
//        PublicKey publicKey = crt.getPublicKey();
//        return publicKey;
//    }

//    private static void createKeyFile(Object key, String filePath) throws Exception {
//        FileOutputStream fos = new FileOutputStream(filePath);
//        ObjectOutputStream oos = new ObjectOutputStream(fos);
//        oos.writeObject(key);
//        oos.flush();
//        oos.close();
//    }


//    public static void main(String[] args){
//        RSAEncrypt rsaEncrypt= new RSAEncrypt();
//        //rsaEncrypt.genKeyPair();
//
//        //加载公钥
//        try {
//            rsaEncrypt.loadPublicKey(RSAEncrypt.DEFAULT_PUBLIC_KEY);
//            System.out.println("加载公钥成功");
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            System.err.println("加载公钥失败");
//        }
//
//        //加载私钥
//        try {
//            rsaEncrypt.loadPrivateKey(RSAEncrypt.DEFAULT_PRIVATE_KEY);
//            System.out.println("加载私钥成功");
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            System.err.println("加载私钥失败");
//        }
//
//        //测试字符串
//        String encryptStr= "Test String chaijunkun";
//
//        try {
//            //加密
//            byte[] cipher = rsaEncrypt.encrypt(rsaEncrypt.getPublicKey(), encryptStr.getBytes());
//            //解密
//            byte[] plainText = rsaEncrypt.decrypt(rsaEncrypt.getPrivateKey(), cipher);
//            System.out.println("密文长度:"+ cipher.length);
//            System.out.println(RSAEncrypt.byteArrayToString(cipher));
//            System.out.println("明文长度:"+ plainText.length);
//            System.out.println(RSAEncrypt.byteArrayToString(plainText));
//            System.out.println(new String(plainText));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//        }

//        StringBuffer cmd = new StringBuffer();
//        cmd.append("/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/bin/keytool ");
//        cmd.append("-genkey -v -alias tyzyssl -keyalg RSA -keysize 1024 -validity 10000 -storetype JCEKS ");
//        cmd.append("-keystore /Users/zhuguiqiu/java-workspace/tyzybaas/target/config/client/organization/security/tyzyssl.jks ");
//        cmd.append("-keypass 123456789 -storepass 123456789 ");
//        cmd.append("-dname 'CN=tyzybaas,OU=tyzy,O=tyzy,L=BEIJING,ST=BEIJING,C=cn' ");
//
//        System.out.println(cmd.toString());
////        keytool -importkeystore  -destkeystore /Users/zhuguiqiu/java-workspace/tyzybaas/target/config/client/tyzyssl.jks -deststoretype pkcs12
//        StringBuffer cmd1 = new StringBuffer();
//        cmd1.append("/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home/bin/keytool ");
//        cmd1.append("-importkeystore -srckeystore /Users/zhuguiqiu/java-workspace/tyzybaas/target/config/client/tyzyssl.jks ");
//        cmd1.append("-deststoretype pkcs12");
//        System.out.println(cmd1.toString());
//        //keytool -exportcert -alias tyzyssl -file /Users/zhuguiqiu/java-workspace/tyzybaas/target/config/client/organization/security/tyzy.crt -keystore /Users/zhuguiqiu/java-workspace/tyzybaas/target/config/client/organization/security/tyzyssl.jks -storepass 123456789 -rfc -storetype JCEKS
//        try {
//            Runtime.getRuntime().exec(cmd.toString());
////            Runtime.getRuntime().exec(cmd1.toString());
////            ps.destroy();
//            //int v = ps.exitValue();
////            System.out.println("v:" + v);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }

//        String privatePath = "F:/Key/testPri.key"; // 准备导出的私钥
//        String publicPath = "F:/Key/testPub.key"; // 准备导出的公钥
//        PrivateKey privateKey = getPrivateKeyFromStore();
//        createKeyFile(privateKey, privatePath);
//        PublicKey publicKey = getPublicKeyFromCrt();
//        createKeyFile(publicKey, publicPath);
//    }
}
