package com.hzgy.core.common;


public enum SQLOperator {

    EQU("等于", "="),
    NEQ("不等于", "<>"),
    NEQ2("不等于", "!="),
    LSS("小于", "<"),
    LEQ("小于等于", "<="),
    GTR("大于", ">"),
    GEQ("大于等于", ">="),
    IN("在集合中", "in"),
    NOTIN("不在集合中", "not in"),
    ISNULL("为空", "is null"),
    ISNOTNULL("不为空", "is not null");

    /**
     * 枚举名称
     */
    private String name;

    /**
     * 返回码
     */
    private String code;

    // 构造方法
    private SQLOperator(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
