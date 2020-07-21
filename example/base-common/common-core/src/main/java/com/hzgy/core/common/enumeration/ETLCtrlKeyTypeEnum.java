package com.hzgy.core.common.enumeration;

public enum ETLCtrlKeyTypeEnum implements BaseEnum {

    STRING(1,"字符串"),
    NUMBER(2,"数值"),
    DATE(3,"时间"),
    STRING_DATE(13,"字符串_时间"),
    STRING_STRING_STRING(111,"字符串_字符串_字符串"),
    STRING_STRING_DATE(113,"字符串_字符串_时间"),
    STRING_DATE_DATE(133,"字符串_时间_时间");

    private int typeId;

    private String typeName;

    ETLCtrlKeyTypeEnum(int typeId, String typeName){
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public static boolean isContain(int typeId) {
        ETLCtrlKeyTypeEnum[] enums = values();
        for (ETLCtrlKeyTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        ETLCtrlKeyTypeEnum[] enums = values();
        for (ETLCtrlKeyTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
