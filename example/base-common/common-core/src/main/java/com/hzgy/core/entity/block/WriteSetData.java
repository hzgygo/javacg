package com.hzgy.core.entity.block;

import java.io.Serializable;

/**
 * 交易写集数据
 */
public class WriteSetData implements Serializable{
    private static final long serialVersionUID = -7038030952980615258L;

    /**
     * 命名空间
     */
    private String namespace;

    /**
     * 写集索引
     */
    private Integer index;

    /**
     * 写集key
     */
    private String key;

    /**
     * 写集值
     */
    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
