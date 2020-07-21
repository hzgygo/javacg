package com.hzgy.core.common.enumeration;

public enum RoleTypeEnum implements BaseEnum {

    AUTH_ROLE(0,"功能角色"),

    DATA_ROLE(1,"数据角色");

    private int typeId;

    private String typeName;

    RoleTypeEnum(int typeId, String typeName){
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
        RoleTypeEnum[] enums = values();
        for (RoleTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        RoleTypeEnum[] enums = values();
        for (RoleTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
