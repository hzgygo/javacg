package com.hzgy.core.common;



/**
 * 数字转换枚举类型
 */
public enum NumberFormat {

    NUM_ONE(1,"One"),
    NUM_TWO(2,"Two"),
    NUM_THREE(3,"Three"),
    NUM_FOUR(4,"Four"),
    NUM_FIVE(5,"Five"),
    NUM_SIX(6,"Six"),
    NUM_SEVEN(7,"Seven"),
    NUM_EIGHT(8,"Eight"),
    NUM_NINE(9,"Nine"),
    NUM_TEN(10,"Ten");

    private Integer num;
    private String value;

    NumberFormat(Integer num,String value) {
        this.num = num;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
