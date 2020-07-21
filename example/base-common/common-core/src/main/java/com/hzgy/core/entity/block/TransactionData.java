package com.hzgy.core.entity.block;

import java.io.Serializable;

/**
 * 区块中的交易数据
 */
public class TransactionData implements Serializable {

    private static final long serialVersionUID = 1755089947715727272L;

    /**
     * 通道ID
     */
    private String channelId;

    /**
     * 交易ID
     */
    private String transactionId;

    /**
     * 交易验证码
     */
    private String validationCode;

    /**
     *交易时间戳
     */
    private Long timestamp;

    /**
     * 交易创建或提交者ID-证书id
     */
    private  String createId;

    /**
     *交易创建或提交者msp证书用户ID
     */
    private  String createMspid;

    /**
     * 交易是否使用交易验证码标记
     */
    private Boolean isValid;

    /**
     * 防止重复交易的nonce
     */
    private  String nonce;


    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public String getCreateMspid() {
        return createMspid;
    }

    public void setCreateMspid(String createMspid) {
        this.createMspid = createMspid;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

}
