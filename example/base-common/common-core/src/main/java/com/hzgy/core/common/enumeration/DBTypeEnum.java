package com.hzgy.core.common.enumeration;

public enum DBTypeEnum implements BaseEnum {

    MYSQL(0,"mysql"),
    ORACLE  (1,"oracle"),
    SQLSERVER(2,"sqlserver");

    private int typeId;

    private String typeName;

    DBTypeEnum(int typeId, String typeName){
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
        DBTypeEnum[] enums = values();
        for (DBTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return true;
            }
        }
        return false;
    }

    public static String getDesc(int typeId) {
        DBTypeEnum[] enums = values();
        for (DBTypeEnum enumeration : enums) {
            if (enumeration.getTypeId() == typeId) {
                return enumeration.getTypeName();
            }
        }
        return null;
    }
}
