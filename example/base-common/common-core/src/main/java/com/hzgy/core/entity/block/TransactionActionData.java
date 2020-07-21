package com.hzgy.core.entity.block;

import java.io.Serializable;

/**
 * 交易执行数据
 */
public class TransactionActionData implements Serializable {

    private static final long serialVersionUID = -3480826398305110809L;

    /**
     * 返回状态
     */
    private Integer responseStatus;

    /**
     * 返回消息
     */
    private String responseMessage;

    /**
     * 背书数量
     */
    private Integer endorsementsCount;

    /**
     * 链码输入参数数量
     */
    private Integer  chaincodeInputArgsCount;

    /**
     * 提案返回状态
     */
    private Integer  proposalResponseStatus;

    /**
     * 提案执行内容
     */
    private String proposalResponsePayload;

    public Integer getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(Integer responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Integer getEndorsementsCount() {
        return endorsementsCount;
    }

    public void setEndorsementsCount(Integer endorsementsCount) {
        this.endorsementsCount = endorsementsCount;
    }

    public Integer getChaincodeInputArgsCount() {
        return chaincodeInputArgsCount;
    }

    public void setChaincodeInputArgsCount(Integer chaincodeInputArgsCount) {
        this.chaincodeInputArgsCount = chaincodeInputArgsCount;
    }

    public Integer getProposalResponseStatus() {
        return proposalResponseStatus;
    }

    public void setProposalResponseStatus(Integer proposalResponseStatus) {
        this.proposalResponseStatus = proposalResponseStatus;
    }

    public String getProposalResponsePayload() {
        return proposalResponsePayload;
    }

    public void setProposalResponsePayload(String proposalResponsePayload) {
        this.proposalResponsePayload = proposalResponsePayload;
    }
}
