package com.hzgy.core.common.enumeration;

public enum AttrValueTypeEnum implements BaseEnum {

    STRING(0,"字符串"),
    ENUM_STRING(1,"枚举字符串"),
    INT(2,"整数"),
    DOUBLE(3,"数值"),
    DATE(4,"日期"),
    TIME(5,"时间"),
    DATE_TIME(6,"日期时间"),
    ID(7,"ID"),
    SIGN(8,"标志位"),
    JSON(9,"json文档"),
    DECIMAL(10,"金额型");

    private int typeId;

    private String typeName;

    AttrValueTypeEnum(int typeId, String typeName){
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
        AttrValueTypeEnum[] enums = values();
        for (AttrValueTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        AttrValueTypeEnum[] enums = values();
        for (AttrValueTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
