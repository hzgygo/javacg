package com.hzgy.core.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.Serializable;

/**
 * 业务返回结果通用封装
 */
public class TPResultData<T> implements Serializable {

    private static final long serialVersionUID = -5757667553780744793L;

    /**
     * 业务返回码
     */
    private String code;

    /**
     * 返回业务说明信息
     */
    private String msg;

    /**
     * 返回业务数据
     */
    private T data;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String jsonString = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
        if(1024 < jsonString.length()){
            jsonString = jsonString.substring(0,1024);
            jsonString = jsonString + "...省略...";
        }

        return "TPResultData{" +
                "code=" + code +
                ", data=" + jsonString +
                ", msg=" + msg +
                '}';
    }
}
