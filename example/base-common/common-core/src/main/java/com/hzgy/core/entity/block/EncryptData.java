package com.hzgy.core.entity.block;

import com.hzgy.core.entity.BaseVo;
import io.swagger.annotations.ApiModel;


@ApiModel(value = "dataEncryptInput", description = "BAAS系统接口请求参数")
public class EncryptData extends BaseVo {


    private static final long serialVersionUID = 7541362397485427913L;

    /**
     * 客户端密码
     */
    private String pwd;
    /**
     * 客户端数据签名
     */
    private String sign;

    /**
     * 客户端公钥
     */
    private String puk;

    /**
     * 客户端业务数据
     */
    private String data;


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPuk() {
        return puk;
    }

    public void setPuk(String puk) {
        this.puk = puk;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}


