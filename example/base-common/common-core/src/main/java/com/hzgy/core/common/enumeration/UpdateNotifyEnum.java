package com.hzgy.core.common.enumeration;

public enum UpdateNotifyEnum implements BaseEnum {

    NO_NOTIFY(0,"不通知"),

    NOTIFY(1,"通知");

    private int typeId;

    private String typeName;

    UpdateNotifyEnum(int typeId, String typeName){
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
        UpdateNotifyEnum[] enums = values();
        for (UpdateNotifyEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        UpdateNotifyEnum[] enums = values();
        for (UpdateNotifyEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
