package com.hzgy.core.common.enumeration;

/** 时间单位 */
public enum TimeUnitEnum implements BaseEnum {

    DAY_FREQUENCY("DD","日"),
    TRANS_DAY_FREQUENCY("TD","交易日"),
    WEEK_FREQUENCY("WW","周"),
    MONTH_FREQUENCY("MM","月"),
    QUARTER_FREQUENCY("QQ","季"),
    HALF_A_YEAR_FREQUENCY("HY","半年"),
    YEAR_FREQUENCY("YY","年"),
    MINUTE_FREQUENCY("MN","分钟"),
    HOUR_FREQUENCY("HH","小时"),
    SECOND_FREQUENCY("SS","秒"),
    MILLISECOND_FREQUENCY("MS","毫秒");

    private String typeCode;

    private int typeId;

    private String typeName;

    TimeUnitEnum(String typeCode, String typeName){
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public static boolean isContain(String typeCode) {
        TimeUnitEnum[] enums = values();
        for (TimeUnitEnum enumeration : enums) {
            if (enumeration.getTypeCode().equals(typeCode)) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(String typeCode) {
        TimeUnitEnum[] enums = values();
        for (TimeUnitEnum enumeration : enums) {
            if (enumeration.getTypeCode().equals(typeCode)) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
