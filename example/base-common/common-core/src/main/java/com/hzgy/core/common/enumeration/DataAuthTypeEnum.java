package com.hzgy.core.common.enumeration;

/** 数据权限类型 */
public enum DataAuthTypeEnum implements BaseEnum {

    FORWARD_AUTH(1,"正向授权"),

    INVERSE_AUTH(2,"反向授权");

    private int typeId;

    private String typeName;

    DataAuthTypeEnum(int typeId, String typeName){
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
        DataAuthTypeEnum[] enums = values();
        for (DataAuthTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        DataAuthTypeEnum[] enums = values();
        for (DataAuthTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
