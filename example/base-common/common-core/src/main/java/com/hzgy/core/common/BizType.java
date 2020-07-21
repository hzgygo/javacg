package com.hzgy.core.common;


/**
 * 业务中各种状态类型
 */
public enum BizType {

    /**
     * 业务类型
     */
    TYPE_0("0",0),
    TYPE_1("1",1),
    TYPE_2("2",2),
    TYPE_3("3",3),
    TYPE_4("4",4),
    TYPE_5("5",5),
    TYPE_6("6",6),
    TYPE_7("7",7),
    TYPE_8("8",8),
    TYPE_9("9",9),
    TYPE_10("10",10);

    /**
     * 状态名称
     */
    private String name;

    /**
     * 状态值
     */
    private Integer value;

    // 构造方法
    private BizType(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
