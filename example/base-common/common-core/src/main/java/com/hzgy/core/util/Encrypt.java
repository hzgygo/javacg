package com.hzgy.core.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encrypt {

    public static byte[] key = {-42, 1, 25, 25, -57, 84, 67, 32};

    public static final String getMD5(String text) {
        // return text;
        byte[] intext = text.getBytes();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] md5rslt = md5.digest(intext);
        StringBuffer verifyMsg = new StringBuffer();
        for (int i = 0; i < md5rslt.length; i++) {
            int hexChar = 0xFF & md5rslt[i];
            String hexString = Integer.toHexString(hexChar);
            hexString = (hexString.length() == 1) ? "0" + hexString : hexString;
            verifyMsg.append(hexString);
        }
        return verifyMsg.toString().toLowerCase();
    }

    static byte[] encrypt(byte[] data, byte[] b) throws Exception {
        DESKeySpec dks = new DESKeySpec(b);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        SecureRandom sr = new SecureRandom();
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        byte[] encryptedData = cipher.doFinal(data);
        return encryptedData;
    }

    static byte[] decrypt(byte[] data, byte[] b) throws Exception {
        DESKeySpec dks = new DESKeySpec(b);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        SecureRandom sr = new SecureRandom();
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        byte[] decryptedData = cipher.doFinal(data);
        return decryptedData;
    }

    static byte[] getKey() throws Exception {
        SecureRandom sr = new SecureRandom();
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(sr);
        SecretKey key = kg.generateKey();
        byte[] b = key.getEncoded();
        return b;
    }

    // public static void main(String[] args) throws Exception {
    // byte[] b = key;//Encrypt.getKey();
    // System.out.println("Length:" + b.length);
    // for(byte temp : b) {
    // System.out.print(temp + " ");
    // }
    // System.out.println("");
    //
    // String s = "I love you.";
    // System.out.println(s);
    // byte[] encryptedData = Encrypt.encrypt(s.getBytes(), b);
    // System.out.println(new String(encryptedData));
    // byte[] decryptedData = Encrypt.decrypt(encryptedData, b);
    // System.out.println(new String(decryptedData));
    // }
//    public static void main(String[] args) throws Exception {
//        System.out.println(getMD5("admin"));
//        System.out.println(getMD5(getMD5("admin")));
//    }
}
