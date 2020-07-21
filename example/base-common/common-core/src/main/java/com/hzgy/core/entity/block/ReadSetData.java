package com.hzgy.core.entity.block;

import java.io.Serializable;

/**
 * 交易读集数据
 */
public class ReadSetData implements Serializable {
    private static final long serialVersionUID = 3771566044966989851L;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 读集索引
     */
    private Integer index;

    /**
     * 读集key
     */
    private String key;

    /**
     * 读集中读的时候区块的高度
     */
    private Long readVersionBlockNum;

    /**
     * 读集中读的时候的交易数量
     */
    private Long readVersionTxNum;

    /**
     * 读集版本号-由区块高度和交易数量功能组成
     */
    private String version;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Long getReadVersionBlockNum() {
        return readVersionBlockNum;
    }

    public void setReadVersionBlockNum(Long readVersionBlockNum) {
        this.readVersionBlockNum = readVersionBlockNum;
    }

    public Long getReadVersionTxNum() {
        return readVersionTxNum;
    }

    public void setReadVersionTxNum(Long readVersionTxNum) {
        this.readVersionTxNum = readVersionTxNum;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
