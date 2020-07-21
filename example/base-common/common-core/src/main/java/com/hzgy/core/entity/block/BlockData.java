package com.hzgy.core.entity.block;

import com.hzgy.core.entity.BaseVo;

/**
 * 区块数据
 */
public class BlockData extends BaseVo {

    private static final long serialVersionUID = 7795126254365152652L;

    /**
     * 区块地址
     */
    private String address;

    /**
     * 调链码返回数据
     */
    private String data;

    /**
     * 登记数据摘要
     */
    private String dataHash;

//    /**
//     * 加密自定义数据
//     */
//    private String encryptData;

    /**
     * 交易id
     */
    private String txid;

    /**
     * 区块高度
     */
    private Long height;

    /**
     * 区块哈希
     */
    private String hash;

    /**
     * 上一个区块hash
     */
    private String previousHash;

    /**
     * 计算区块哈希
     */
    private String calculatedBlockHash;

    /**
     * 区块中交易的数量
     */
    private Integer envelopeCount;

    /**
     * 背书数据对象
     */
    private EndorserData endorserData;

    /**
     * 交易
     */
    private TransactionActionData transactionActionData;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getCalculatedBlockHash() {
        return calculatedBlockHash;
    }

    public void setCalculatedBlockHash(String calculatedBlockHash) {
        this.calculatedBlockHash = calculatedBlockHash;
    }

    public Integer getEnvelopeCount() {
        return envelopeCount;
    }

    public void setEnvelopeCount(Integer envelopeCount) {
        this.envelopeCount = envelopeCount;
    }

    public EndorserData getEndorserData() {
        return endorserData;
    }

    public void setEndorserData(EndorserData endorserData) {
        this.endorserData = endorserData;
    }

    public TransactionActionData getTransactionActionData() {
        return transactionActionData;
    }

    public void setTransactionActionData(TransactionActionData transactionActionData) {
        this.transactionActionData = transactionActionData;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataHash() {
        return dataHash;
    }

    public void setDataHash(String dataHash) {
        this.dataHash = dataHash;
    }
}
