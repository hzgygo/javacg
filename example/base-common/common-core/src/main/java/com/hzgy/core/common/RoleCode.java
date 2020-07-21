package com.hzgy.core.common;


/**
 * 枚举类型
 */
public enum RoleCode {

    READ("role_read",1),
    WRITE("role_write", 2),
    TRUST("role_trust", 3),
    ANONYMOUS("role_anonymous", 4);

    /**
     * 枚举名称
     */
    private String code;

    /**
     * 返回码
     */
    private Integer index;

    // 构造方法
    private RoleCode(String code, Integer index) {
        this.code = code;
        this.index = index;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
