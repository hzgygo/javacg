package com.hzgy.core.common.enumeration;

public enum ClearTypeEnum implements BaseEnum{

    NORMAL(0,"正常"),
    EVENT_DESC_EXCEPTION(1,"事件描述异常"),
    EVENT_ATTR_EXCEPTION(2,"事件因子异常"),
    STATUS_EXCEPTION(3,"状态异常"),
    PEOPLE_EXCEPTION(4,"人为异常");

    private int typeId;

    private String typeName;

    ClearTypeEnum(int typeId, String typeName){
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
        ClearTypeEnum[] enums = values();
        for (ClearTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        ClearTypeEnum[] enums = values();
        for (ClearTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
