package com.hzgy.core.common.enumeration;

public enum ClearStatusEnum implements BaseEnum{

    UNEXECUTED(0,"未处理"),

    EXECUTED(1,"已处理"),

    EXECUTEDING(2,"正在处理");

    private int typeId;

    private String typeName;

    ClearStatusEnum(int typeId, String typeName){
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
        ClearStatusEnum[] enums = values();
        for (ClearStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        ClearStatusEnum[] enums = values();
        for (ClearStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
