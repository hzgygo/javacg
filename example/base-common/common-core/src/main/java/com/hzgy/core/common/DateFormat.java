package com.hzgy.core.common;


/**
 * 日期格式化枚举类型
 */
public enum DateFormat {

    DATE_YEAR("yyyy"),
    DATE_MONTH("yyyyMM"),
    DATE("yyyyMMdd"),
    DATE_HOUR("yyyyMMddHH"),
    DATE_MINUTE("yyyyMMddHHmm"),
    DATE_SECOND("yyyyMMddHHmmss"),
    DATE_MILLISECOND("yyyyMMddHHmmssMMS"),

    DATE_MONTH_HYPHEN("yyyy-MM"),
    DATE_HYPHEN("yyyy-MM-dd"),
    DATE_MINUTE_HYPHEN("yyyy-MM-dd HH:mm"),
    DATE_TIME_HYPHEN("yyyy-MM-dd HH:mm:ss"),
    DATE_TIME_MONTH("MM-dd HH:mm:ss"),
    DATE_MONTH_VIRGULE("yyyy/MM"),
    DATE_VIRGULE("yyyy/MM/dd"),
    DATE_MINUTE_VIRGULE("yyyy/MM/dd HH:mm"),
    DATE_TIME_VIRGULE("yyyy/MM/dd HH:mm:ss"),
    TIME_STAMP_10("010d%"),
    TIME_STAMP_13("013d%"),
    NONE("[NONE]");

    private String value;

    DateFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
