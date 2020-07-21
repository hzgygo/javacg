package com.hzgy.core.common.enumeration;

public enum ExtractPlanEnum implements BaseEnum {

    MANY_TIME_EXTRACT(0,"多次采集"),

    SINGLE_EXTRACT(1,"一次采集");

    private int typeId;

    private String typeName;

    ExtractPlanEnum(int typeId, String typeName){
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
        ExtractPlanEnum[] enums = values();
        for (ExtractPlanEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        ExtractPlanEnum[] enums = values();
        for (ExtractPlanEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
