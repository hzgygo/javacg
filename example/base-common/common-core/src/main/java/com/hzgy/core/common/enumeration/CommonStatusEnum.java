package com.hzgy.core.common.enumeration;

public enum CommonStatusEnum implements BaseEnum {

    CLOSE(0,"禁用"),

    OPEN(1,"启用"),

    FALSE(0,"否"),

    TRUE(1,"是");

    private int typeId;

    private String typeName;

    CommonStatusEnum(int typeId, String typeName){
        this.typeId = typeId;
        this.typeName = typeName;
    };

    public int getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public static boolean isContain(int typeId) {
        CommonStatusEnum[] enums = values();
        for (CommonStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        CommonStatusEnum[] enums = values();
        for (CommonStatusEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
