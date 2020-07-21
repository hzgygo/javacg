package com.hzgy.core.entity.block;

import java.security.PublicKey;


/**
 * 解密参数对象
 */
public class DecryptData implements java.io.Serializable{

    private static final long serialVersionUID = 8401214479427803543L;

    /**
     * 解密的客户端（调用端）公钥对象
     */
    private PublicKey publicKey;

    /**
     * 公钥字符串
     */
    private String puk;

    /**
     * 解密后的业务参数
     */
    private String data;

    /**
     * aes加密密码
     */
    private byte[] pwdBytes;

    /**
     * 签名
     */
    private byte[] signBytes;

    /**
     * 客户端（调用端）公钥
     */
    private byte[] pukBytes;

    /**
     * 业务参数数据
     */
    private byte[] dataBytes;


    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public byte[] getPwdBytes() {
        return pwdBytes;
    }

    public void setPwdBytes(byte[] pwdBytes) {
        this.pwdBytes = pwdBytes;
    }

    public byte[] getSignBytes() {
        return signBytes;
    }

    public void setSignBytes(byte[] signBytes) {
        this.signBytes = signBytes;
    }

    public byte[] getPukBytes() {
        return pukBytes;
    }

    public void setPukBytes(byte[] pukBytes) {
        this.pukBytes = pukBytes;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }

    public String getPuk() {
        return puk;
    }

    public void setPuk(String puk) {
        this.puk = puk;
    }
}
