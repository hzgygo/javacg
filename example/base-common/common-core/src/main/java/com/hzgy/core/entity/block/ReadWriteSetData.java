package com.hzgy.core.entity.block;

import java.io.Serializable;
import java.util.List;

/**
 * 读写集数据
 */
public class ReadWriteSetData implements Serializable {

    private static final long serialVersionUID = 373576687693277032L;

    /**
     * 读集合列表
     */
    private List<ReadSetData> listReadSetData;

    /**
     * 写集合列表
     */
    private List<WriteSetData> listWriteSetData;

    public List<ReadSetData> getListReadSetData() {
        return listReadSetData;
    }

    public void setListReadSetData(List<ReadSetData> listReadSetData) {
        this.listReadSetData = listReadSetData;
    }

    public List<WriteSetData> getListWriteSetData() {
        return listWriteSetData;
    }

    public void setListWriteSetData(List<WriteSetData> listWriteSetData) {
        this.listWriteSetData = listWriteSetData;
    }
}
