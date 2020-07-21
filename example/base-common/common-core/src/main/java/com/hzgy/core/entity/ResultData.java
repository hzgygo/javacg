package com.hzgy.core.entity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hzgy.core.common.ReturnCode;

import java.io.Serializable;

/**
 * 业务返回结果通用封装
 */
public class ResultData<T> implements Serializable {

    private static final long serialVersionUID = -5757667553780744793L;

    /**
     * 业务返回码
     */
    private Integer code;

    /**
     * 返回业务说明信息
     */
    private String message;

    /**
     * 返回业务数据
     */
    private T data;

    private String invokeId;

    public void setResultData(ReturnCode returnCode) {
        this.code = returnCode.getCode();
        this.message = returnCode.getName();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInvokeId() {
        return invokeId;
    }

    public void setInvokeId(String invokeId) {
        this.invokeId = invokeId;
    }

    @Override
    public String toString() {

        String jsonString = JSON.toJSONString(data, SerializerFeature.WriteMapNullValue);
        if(1024 < jsonString.length()){
            jsonString = jsonString.substring(0,1024);
            jsonString = jsonString + "...省略...";
        }

        return "ResultData{" +
                "code=" + code +
                ", data=" + jsonString +
                ", message=" + message +
                ", invokeId=" + invokeId +
                '}';
    }
}
