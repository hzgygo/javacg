package com.hzgy.core.entity.block;

import java.io.Serializable;

/**
 * 背书数据
 */
public class EndorserData implements Serializable {

    private static final long serialVersionUID = 1258748563372241613L;

    /**
     * 提案签名
     */
    private String signature;

    /**
     * 背书节点证书id-证书内容
     */
    private String id;

    /**
     * 背书节点mspid
     */
    private String mspid;


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMspid() {
        return mspid;
    }

    public void setMspid(String mspid) {
        this.mspid = mspid;
    }
}
