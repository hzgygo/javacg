package com.hzgy.core.common.enumeration;

public enum ExtractModeEnum implements BaseEnum {

    DATABASE_EXTRACT(0,"数据库采集"),

    INTERFACE_EXTRACT(1,"接口采集");

    private int typeId;

    private String typeName;

    ExtractModeEnum(int typeId, String typeName){
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
        ExtractModeEnum[] extractModeEnums = values();
        for (ExtractModeEnum enumeration : extractModeEnums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        ExtractModeEnum[] extractModeEnums = values();
        for (ExtractModeEnum enumeration : extractModeEnums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
